package com.example.engineeringapp.UI_login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.engineeringapp.databinding.ActivityRegistrationBinding
import com.example.engineeringapp.userdata_collect.UserDataCollectInformation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.title = "Registration Page"

        //button register functionality

        binding.btnRegister.setOnClickListener {

            val userEmail: String = binding.registerEmail.text.toString().trim { it <= ' ' }
            val userPass: String = binding.registrationPassword.text.toString().trim { it <= ' ' }
            val userPassRep: String = binding.repeatPassword.text.toString().trim { it <= ' ' }

            if (userEmail.isEmpty()) {
                binding.registerEmail.error = "Please enter an email"
            }
            else if (userPass.isEmpty()) {
                binding.registrationPassword.error = "Please enter a password"
            }
            else if (userPassRep.isEmpty()) {
                binding.repeatPassword.error = "Please repeat a password"
            }
            else if (userPass != userPassRep) {
                binding.repeatPassword.error = "Passwords don't match"
            }
            else {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(userEmail, userPass)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val firebaseUser: FirebaseUser = task.result!!.user!!
                            Toast.makeText(
                                this@RegistrationActivity,
                                "You were registered successfully.",
                                Toast.LENGTH_SHORT
                            ).show()

                            val intent =
                                Intent(
                                    this@RegistrationActivity,
                                    UserDataCollectInformation::class.java
                                )
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("email", userEmail)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                this@RegistrationActivity,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                     }
            }
        }
        //Go to Login Activity:
        binding.loginActivityText.setOnClickListener {
            onBackPressed()
        }
    }
    //hide action bar
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus){
            this.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN)
        }
    }
}