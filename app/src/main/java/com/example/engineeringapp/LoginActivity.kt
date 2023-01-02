package com.example.engineeringapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        this.title = "Log In Page"

        val btnLogin = findViewById<Button>(R.id.btn_login)
        var log_email = findViewById<EditText>(R.id.login_email)
        var log_pass = findViewById<EditText>(R.id.login_password)
        btnLogin.setOnClickListener {
            when{
                TextUtils.isEmpty(log_email.text.toString().trim { it <= ' '}) -> {
                    Toast.makeText(
                        this@LoginActivity,
                        "Please enter an email",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(log_pass.text.toString().trim { it <= ' '}) -> {
                    Toast.makeText(
                        this@LoginActivity,
                        "Please enter a password",
                        Toast.LENGTH_SHORT
                    ).show()
                } else -> {

                val userEmail: String = log_email.text.toString().trim { it <=  ' '}
                val userPass: String = log_pass.text.toString().trim{ it <=  ' '}

                FirebaseAuth.getInstance().signInWithEmailAndPassword(userEmail, userPass).addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        Toast.makeText(
                            this@LoginActivity,
                            "You were logged in successfully.",
                            Toast.LENGTH_SHORT
                        ).show()

                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        intent.putExtra("user_id",FirebaseAuth.getInstance().currentUser!!.uid)
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
        }


        //go to Registartion activity
        val registration = findViewById<TextView>(R.id.registration_activity_text)
        registration.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

    }

}