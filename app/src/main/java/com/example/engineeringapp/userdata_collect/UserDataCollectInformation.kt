package com.example.engineeringapp.userdata_collect

import android.content.Intent
import android.os.Bundle
import android.view.View
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
    //hide action bar
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus){
            this.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN)
        }
    }
}