package com.cat.cattasticapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class Register : AppCompatActivity() {
    private lateinit var edEmailReg: EditText
    private lateinit var edPasswordReg: EditText
    private lateinit var edConfirmPassword: EditText
    private lateinit var mAuth: FirebaseAuth
    private lateinit var btnReg: Button
    private lateinit var backArrow : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Typecast
        edEmailReg = findViewById(R.id.edEmailReg)
        edPasswordReg = findViewById(R.id.edPasswordReg)
        edConfirmPassword = findViewById(R.id.edConfirmPassword)
        mAuth = FirebaseAuth.getInstance()
        btnReg = findViewById(R.id.btnReg)
        backArrow = findViewById(R.id.backArrow)

        // Initialize Firebase
        FirebaseApp.initializeApp(this)
        mAuth = FirebaseAuth.getInstance()

        btnReg.setOnClickListener {
            signUpUser()
        }
        backArrow.setOnClickListener {
            val intent = Intent(this@Register, Login::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun signUpUser() {
        val email = edEmailReg.text.toString()
        val pass = edPasswordReg.text.toString()
        val confirmPassword = edConfirmPassword.text.toString()

        // Check if fields are not empty
        if (email.isBlank() || pass.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }

        // Check if passwords match
        if (pass != confirmPassword) {
            Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT).show()
            return
        }

        // Attempt to create a new user
        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Successfully Signed Up", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@Register, Login::class.java)
                startActivity(intent)
                finish()
            } else {
                // Handle different error cases
                when (task.exception) {
                    is FirebaseAuthWeakPasswordException -> {
                        Toast.makeText(this, "Weak password! Please choose a stronger password.", Toast.LENGTH_SHORT).show()
                    }
                    is FirebaseAuthUserCollisionException -> {
                        Toast.makeText(this, "User already exists! Please use a different email.", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(this, "Sign Up Failed! ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Sign Up Failed! ${it.message}", Toast.LENGTH_SHORT).show()
        }
    }
}