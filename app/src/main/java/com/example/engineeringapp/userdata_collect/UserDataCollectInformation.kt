package com.example.engineeringapp.userdata_collect

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.engineeringapp.MainActivity
import com.example.engineeringapp.R

class UserDataCollectInformation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_data_collect_information)
        this.title = "Data collect"
        val btn_next = findViewById<Button>(R.id.btn_next)
        btn_next.setOnClickListener {
            val intent = Intent(this@UserDataCollectInformation, MainActivity::class.java)
            startActivity(intent)
        }
    }
}