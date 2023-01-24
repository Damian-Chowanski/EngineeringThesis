package com.example.engineeringapp.menuItems

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.engineeringapp.MainActivity
import com.example.engineeringapp.Module.UserData
import com.example.engineeringapp.R
import com.example.engineeringapp.databinding.ActivitySetingsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.serialization.json.Json

class SetingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySetingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val user = intent.getSerializableExtra("UserData") as UserData

        binding.etFirstname.setText(user.firstname)
        binding.etLastname.setText(user.lastname)
        binding.etStreet.setText(user.street)
        binding.etZipCode.setText(user.zipCode)
        binding.etCity.setText(user.city)
        binding.etCountry.setText(user.country)
        binding.etPhoneNumber.setText(user.phoneNumber)
        binding.etEmail.setText(FirebaseAuth.getInstance().currentUser!!.email)

    }
}
