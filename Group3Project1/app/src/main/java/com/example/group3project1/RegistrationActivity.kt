/* @Author -Jojina Thomas */
package com.example.group3project1
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.group3project1.databinding.ActivityRegistrationBinding
import com.google.firebase.auth.FirebaseAuth

class RegistrationActivity : AppCompatActivity() {

    // Binding object for the layout
   private lateinit var binding:ActivityRegistrationBinding
    // Firebase authentication instance
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the layout using View Binding
        binding =ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
// Initialize Firebase authentication
        firebaseAuth = FirebaseAuth.getInstance()
        val backbutton : ImageView = findViewById(R.id.backbutton)

        backbutton.setOnClickListener{v->
            val intent = Intent(v.context,IntroActivity::class.java)

            v.context.startActivity(intent)

        }

        // SetOnclick listener for the Registration button
        binding.buttonReg.setOnClickListener{
            val email=binding.editTextEmail.text.toString()
            val password=binding.editTextPassword.text.toString()
            val repassword=binding.editTextRePassword.text.toString()

          // Check if email, password, and re password fields are not empty
            if(email.isNotEmpty()&&password.isNotEmpty()&&repassword.isNotEmpty()) {

                // Check if the password and re-password match
                if (password == repassword) {
                   //create a new user
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener{
                            if (it.isSuccessful) {

                                // Log success msg, and nav to  login activity
                                Log.d(TAG, "CreateUserwithEmail:success")
                                Toast.makeText(this, "Account Created Sucessfully.",
                                    Toast.LENGTH_SHORT).show()
                                val intent = Intent(this,LoginActivity::class.java)
                                startActivity(intent)
                            }
                            else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", it.exception)
                                Toast.makeText(
                                    baseContext,
                                    "Authentication failed: ${it.exception?.message}",
                                    Toast.LENGTH_SHORT,
                                ).show()

                            }
                        }


                } else {
                    // Display a error mesage if the passwords fields are not matching
                    Toast.makeText(this, "Pasword is not Matching", Toast.LENGTH_SHORT).show()
                }
            }
                else{
                // Display a error mesage if the email or password fields are empty
                    Toast.makeText(this,"Fields are Empty",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
