/* @Author -Jojina Thomas */
package com.example.group3project1

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.group3project1.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    // Binding object for the layout
    private lateinit var binding: ActivityLoginBinding
    // Firebase authentication instance
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the layout using View Binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
       // Initialize Firebase authentication
        firebaseAuth = FirebaseAuth.getInstance()

        //User moves to Registration page when click on create Account Button
        binding.createAccount.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
          // SetOnclick listener for the login button
        binding.loginbutton.setOnClickListener {
            // created val for email, password, and forget password
            val email = binding.loginEmailId.text.toString()
            val password = binding.loginPassword.text.toString()

           // Verify that  if email and password fields are not empty or not
            if (email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Log success message  and go to the main activity
                            Log.d(ContentValues.TAG, "signInWithEmail:success")

                            Toast.makeText(this, "SucessFully LoggedIn.",
                                Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, CandidateActivity::class.java)

                            // change this once Candidate Activity is done......
                            // val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            // If sign in fails, display a Error message to the user
                            Log.w(ContentValues.TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT,
                            ).show()

                        }
                    }


            } else {
                // Display a error mesage if the email or password fields are empty
                Toast.makeText(this, "Fields are Empty", Toast.LENGTH_SHORT).show();
            }
        }
    }
}



