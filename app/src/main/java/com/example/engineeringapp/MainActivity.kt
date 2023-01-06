package com.example.engineeringapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.engineeringapp.UI_login.LoginActivity
import com.example.engineeringapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val userId = intent.getStringExtra("user_id")
        val userId = FirebaseAuth.getInstance().uid
        val emailId = FirebaseAuth.getInstance().currentUser!!.email

        binding.userId.text = "User_ID :: $userId"

        binding.userEmail.text = "User email :: $emailId"

        val btnLogout: Button = findViewById(R.id.btn_logout)
        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
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