package com.example.engineeringapp.menuItems

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.engineeringapp.Module.UserData
import com.example.engineeringapp.databinding.ActivitySetingsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class SetingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySetingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val user = intent.getSerializableExtra("UserData") as UserData

        binding.etFirstnameUpdate.setText(user.firstname)
        binding.etLastnameUpdate.setText(user.lastname)
        binding.etStreetUpdate.setText(user.street)
        binding.etZipCodeUpdate.setText(user.zipCode)
        binding.etCityUpdate.setText(user.city)
        binding.etCountryUpdate.setText(user.country)
        binding.etPhoneNumberUpdate.setText(user.phoneNumber)
        binding.etEmailUpdate.setText(FirebaseAuth.getInstance().currentUser!!.email)

    }
}
