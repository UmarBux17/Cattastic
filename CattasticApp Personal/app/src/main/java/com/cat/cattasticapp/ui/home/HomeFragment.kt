package com.cat.cattasticapp.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cat.cattasticapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Set up the about text
        binding.aboutTextView.text = """
            Cattastic is an app that helps cat lovers explore and learn more about different cat breeds.
            You can view details about various breeds and also find nearby cat shelters.
            
            Features:
            1. Cat Breeds: Browse through various cat breeds with pictures, descriptions, and characteristics.
            2. Cat Shelters: View a map with locations of nearby cat shelters.
        """.trimIndent()

        // Set up the privacy policy link click listener
        binding.privacyPolicyLink.setOnClickListener {
            openPrivacyPolicy()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun openPrivacyPolicy() {
        val url = "https://st10028265.github.io/"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}
