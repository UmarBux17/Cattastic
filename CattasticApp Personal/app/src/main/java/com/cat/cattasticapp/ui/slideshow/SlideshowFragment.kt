package com.cat.cattasticapp.ui.slideshow

import android.content.pm.PackageManager
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.cat.cattasticapp.PoiDatabase
import com.cat.cattasticapp.PointOfInterest
import com.cat.cattasticapp.databinding.FragmentSlideshowBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.osmdroid.api.IMapController
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class SlideshowFragment : Fragment() {

    private val LOCATION_REQUEST_CODE = 0
    private lateinit var binding: FragmentSlideshowBinding
    private lateinit var mapView: MapView
    private lateinit var mapController: IMapController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Manage permissions
        managePermissions()

        // Initialize map
        setupMap()

        // Set up Floating Action Button
        val fabPoi: FloatingActionButton = binding.fab
        fabPoi.tooltipText = "Add point of interest"
        fabPoi.setOnClickListener {
            addPointOfInterest()
            // Load stored POIs from RoomDB
            loadPointsFromDatabase()
        }
    }

    // Set up map
    // Enable map caching in the setupMap function
    private fun setupMap() {
        val ctx = requireContext().applicationContext

        // Load the default configuration for the map, which includes caching settings
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx))

        // Set the cache path and tile caching settings
        Configuration.getInstance().osmdroidTileCache = ctx.getExternalFilesDir(null)
        Configuration.getInstance().tileFileSystemCacheMaxBytes = 100L * 1024 * 1024 // 100 MB cache size
        Configuration.getInstance().tileFileSystemCacheTrimBytes = 90L * 1024 * 1024 // Trim cache if above 90 MB

        // Initialize map
        mapView = binding.mapView
        mapView.setTileSource(TileSourceFactory.MAPNIK)  // Set tile source
        mapView.setMultiTouchControls(true)  // Enable multi-touch controls

        val startPoint = GeoPoint(-29.8587, 31.0218)  // Durban, South Africa
        mapController = mapView.controller
        mapController.setCenter(startPoint)
        mapController.setZoom(6.0)
    }

    // Add new points of interest on the map and store them in RoomDB
    private fun addPointOfInterest() {
        val hotspots = listOf(
            GeoPoint(-29.8064525, 30.9736967) to "Stray Paws",
            GeoPoint(-29.7841, 30.9965) to "Durban & Coast SPCA",
            GeoPoint(-29.7473, 30.8092) to "Mazarat Animal Rescue"
        )

        val details = mapOf(
            "Stray Paws" to "A non-profit organization helping stray animals in the Durban area.",
            "Durban & Coast SPCA" to "Providing care and shelter for stray and abused animals.",
            "Mazarat Animal Rescue" to "A sanctuary for rescued animals located near Mazarat."
        )

        val poiDao = PoiDatabase.getDatabase(requireContext()).poiDao()

        for ((position, title) in hotspots) {
            val marker = Marker(mapView)
            marker.position = position
            marker.title = title
            marker.snippet = details[title]
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            marker.icon = ContextCompat.getDrawable(requireContext(), org.osmdroid.library.R.drawable.marker_default)
            mapView.overlays.add(marker)

            marker.setOnMarkerClickListener { marker, mapView ->
                marker.showInfoWindow()
                true
            }

            // Save POI to the database
            val poi = PointOfInterest(
                latitude = position.latitude,
                longitude = position.longitude,
                title = title,
                description = details[title] ?: ""
            )
            CoroutineScope(Dispatchers.IO).launch {
                poiDao.insert(poi)
            }
        }

        mapView.invalidate()
    }

    // Load POIs from RoomDB and display them on the map
    private fun loadPointsFromDatabase() {
        val poiDao = PoiDatabase.getDatabase(requireContext()).poiDao()

        CoroutineScope(Dispatchers.IO).launch {
            val pois = poiDao.getAllPOIs()
            withContext(Dispatchers.Main) {
                pois.forEach { poi ->
                    val position = GeoPoint(poi.latitude, poi.longitude)
                    val marker = Marker(mapView)
                    marker.position = position
                    marker.title = poi.title
                    marker.snippet = poi.description
                    marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                    marker.icon = ContextCompat.getDrawable(requireContext(), org.osmdroid.library.R.drawable.marker_default)
                    mapView.overlays.add(marker)

                    marker.setOnMarkerClickListener { marker, mapView ->
                        marker.showInfoWindow()
                        true
                    }
                }
                mapView.invalidate()
            }
        }
    }

    // Manage permissions
    private fun managePermissions() {
        val requestPermissions = mutableListOf<String>()
        if (!isLocationPermissionGranted()) {
            requestPermissions.add(android.Manifest.permission.ACCESS_FINE_LOCATION)
            requestPermissions.add(android.Manifest.permission.ACCESS_COARSE_LOCATION)
        }

        if (requestPermissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                requestPermissions.toTypedArray(),
                LOCATION_REQUEST_CODE
            )
        }
    }

    private fun isLocationPermissionGranted(): Boolean {
        val fineLocation = ActivityCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val coarseLocation = ActivityCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        return fineLocation && coarseLocation
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupMap()
                Toast.makeText(requireContext(), "Location permission granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Location permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
