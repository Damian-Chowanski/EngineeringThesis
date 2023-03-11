package com.example.engineeringapp.UI_login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.engineeringapp.R
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
        title = getString(R.string.registration_page_title)

        binding.btnRegister.setOnClickListener { registerUser() }
        binding.loginActivityText.setOnClickListener { onBackPressed() }

        // Hide action bar
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
    }

    private fun registerUser() {
        val userEmail = binding.registerEmail.text.toString().trim()
        val userPass = binding.registrationPassword.text.toString().trim()
        val userPassRep = binding.repeatPassword.text.toString().trim()

        when {
            userEmail.isEmpty() -> binding.registerEmail.error = getString(R.string.error_empty_field)
            userPass.isEmpty() -> binding.registrationPassword.error = getString(R.string.error_empty_field)
            userPassRep.isEmpty() -> binding.repeatPassword.error = getString(R.string.error_empty_field)
            userPass != userPassRep -> binding.repeatPassword.error = getString(R.string.error_passwords_do_not_match)
            else -> {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(userEmail, userPass)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val firebaseUser: FirebaseUser = task.result!!.user!!
                            Toast.makeText(
                                this,
                                getString(R.string.successful_registration),
                                Toast.LENGTH_SHORT
                            ).show()

                            val intent = Intent(this, UserDataCollectInformation::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("email", userEmail)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                this,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
    }
}
