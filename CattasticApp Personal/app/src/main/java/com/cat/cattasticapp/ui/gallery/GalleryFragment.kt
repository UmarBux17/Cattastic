package com.cat.cattasticapp.ui.gallery

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cat.cattasticapp.BreedDetailActivity
import com.cat.cattasticapp.BreedsAdapter
import com.cat.cattasticapp.R
import com.cat.cattasticapp.RetroInstance
import com.cat.cattasticapp.responseDataClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GalleryFragment : Fragment() {

    private lateinit var breedsRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerView
        breedsRecyclerView = view.findViewById(R.id.breedsRecyclerView)
        breedsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Fetch breeds data
        fetchBreeds()
    }

    private fun fetchBreeds() {
        val apiInterface = RetroInstance.someInterface
        apiInterface.getData().enqueue(object : Callback<responseDataClass> {
            override fun onResponse(call: Call<responseDataClass>, response: Response<responseDataClass>) {
                if (response.isSuccessful) {
                    response.body()?.let { breeds ->
                        breedsRecyclerView.adapter = BreedsAdapter(breeds) { breed ->
                            // Create an intent to launch the BreedDetailActivity
                            val intent = Intent(requireContext(), BreedDetailActivity::class.java).apply {
                                putExtra("breed", breed)
                            }
                            startActivity(intent)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<responseDataClass>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}