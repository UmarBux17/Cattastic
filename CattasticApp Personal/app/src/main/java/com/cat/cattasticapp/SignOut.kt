package com.cat.cattasticapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth


class SignOut : Fragment() {


    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Google Sign-In client
        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(),
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_out, container, false)

        signOut()

        return view
    }

    private fun signOut() {
        // Sign out from Firebase
        FirebaseAuth.getInstance().signOut()

        // Sign out from Google
        mGoogleSignInClient.signOut().addOnCompleteListener(requireActivity()) { task ->
            if (task.isSuccessful) {
                // Successfully signed out of both Firebase and Google
                Toast.makeText(requireActivity(), "You have been signed out", Toast.LENGTH_SHORT).show()

                // After signing out, redirect to the Login activity
                val intent = Intent(requireActivity(), Login::class.java)
                startActivity(intent)
                requireActivity().finish()
            } else {
                // If sign-out fails, display an error message
                Toast.makeText(requireActivity(), "Sign-out failed", Toast.LENGTH_SHORT).show()
            }
        }
    }


}