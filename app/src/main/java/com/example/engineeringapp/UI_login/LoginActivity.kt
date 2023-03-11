package com.example.engineeringapp.UI_login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.engineeringapp.MainActivity
import com.example.engineeringapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.title = "Log In Page"
        // Hide action bar
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        val logEmail = binding.loginEmail
        val logPass = binding.loginPassword
        // button login functionality
        binding.btnLogin.setOnClickListener {
            val userEmail: String = logEmail.text.toString().trim { it <= ' ' }
            val userPass: String = logPass.text.toString().trim { it <= ' ' }

            if (userEmail.isEmpty()) {
                logEmail.error = "Please type an email"
            } else if (userPass.isEmpty()) {
                logPass.error = "Please type a password"
            } else {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(userEmail, userPass)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                            Toast.makeText(
                                this@LoginActivity,
                                "You were logged in successfully.",
                                Toast.LENGTH_SHORT
                            ).show()

                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                            intent.putExtra("email_id", userEmail)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }
            }
        }

        //go to Registartion activity

        binding.registrationActivityText.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }
}