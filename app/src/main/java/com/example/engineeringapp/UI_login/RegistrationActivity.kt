package com.example.engineeringapp.UI_login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.engineeringapp.R
import com.example.engineeringapp.userdata_collect.UserDataCollectInformation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        this.title = "Registration Page"

        val btnRegister = findViewById<Button>(R.id.btn_register)
        var reg_email = findViewById<EditText>(R.id.register_email)
        var reg_pass1 = findViewById<EditText>(R.id.registration_password)
        var reg_pass2 = findViewById<EditText>(R.id.repeat_password)
        btnRegister.setOnClickListener {
            when{
                TextUtils.isEmpty(reg_email.text.toString().trim { it <= ' '}) -> {
                    Toast.makeText(
                        this@RegistrationActivity,
                        "Please enter an email",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(reg_pass1.text.toString().trim { it <= ' '}) -> {
                    Toast.makeText(
                        this@RegistrationActivity,
                        "Please enter a password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(reg_pass2.text.toString().trim {it <= ' '}) -> {
                    Toast.makeText(
                        this@RegistrationActivity,
                        "Please repeat your password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                !TextUtils.equals(reg_pass1.text.toString(),reg_pass2.text.toString()) -> {
                    Toast.makeText(
                        this@RegistrationActivity,
                        "The passwords don't match!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else -> {
                    val userEmail: String = reg_email.text.toString().trim { it <=  ' '}
                    val userPass: String = reg_pass1.text.toString().trim{ it <=  ' '}

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(userEmail, userPass).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val firebaseUser: FirebaseUser = task.result!!.user!!
                            Toast.makeText(
                                this@RegistrationActivity,
                                "You were registered successfully.",
                                Toast.LENGTH_SHORT
                            ).show()

                            val intent = Intent(this@RegistrationActivity, UserDataCollectInformation::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("user_id", firebaseUser.uid)
                            intent.putExtra("email_id", userEmail)
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
        }
        //Go to Login Activity:
        val logInActivity = findViewById<TextView>(R.id.login_activity_text)
        logInActivity.setOnClickListener {
            onBackPressed()
        }
    }
}