package com.example.engineeringapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.engineeringapp.UI_login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val userId = intent.getStringExtra("user_id")
        val userId = FirebaseAuth.getInstance().uid.toString()
        val emailId = FirebaseAuth.getInstance().currentUser!!.email.toString()

        var userIdTextView: TextView = findViewById(R.id.user_id)
        userIdTextView.text = "User_ID :: $userId"

        var userEmailTextView: TextView = findViewById(R.id.user_email)
        userEmailTextView.text = "User email :: $emailId"

        val btnLogout: Button = findViewById(R.id.btn_logout)
        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        }
    }
}