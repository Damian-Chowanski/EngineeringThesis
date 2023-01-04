package com.example.engineeringapp.userdata_collect

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.engineeringapp.R

class UsersInputDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_input_data)
        this.title = "Present yourself!"
    }
}