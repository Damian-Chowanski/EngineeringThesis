package com.example.engineeringapp.userdata_collect

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.engineeringapp.databinding.ActivityUserDataCollectInformationBinding

class UserDataCollectInformation : AppCompatActivity() {
    private lateinit var binding: ActivityUserDataCollectInformationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDataCollectInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.title = "Data collect"

        binding.btnNext.setOnClickListener {
            val intent = Intent(this@UserDataCollectInformation, UsersInputDataActivity::class.java)
            startActivity(intent)
        }
    }
}