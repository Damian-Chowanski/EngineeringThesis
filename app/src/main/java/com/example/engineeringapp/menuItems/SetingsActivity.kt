package com.example.engineeringapp.menuItems

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.engineeringapp.MainActivity
import com.example.engineeringapp.R
import com.example.engineeringapp.databinding.ActivitySetingsBinding

class SetingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySetingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}