package com.example.engineeringapp.menuItems

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.engineeringapp.Module.UserData
import com.example.engineeringapp.Module.Utility
import com.example.engineeringapp.R
import com.example.engineeringapp.databinding.ActivitySettingsBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.auth.FirebaseAuth

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    lateinit var topAppBar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        topAppBar = findViewById(R.id.topAppBar)

        val user = intent.getSerializableExtra("UserData") as UserData

        binding.etFirstnameUpdate.setText(user.firstname)
        binding.etLastnameUpdate.setText(user.lastname)
        binding.etStreetUpdate.setText(user.street)
        binding.etZipCodeUpdate.setText(user.zipCode)
        binding.etCityUpdate.setText(user.city)
        binding.etCountryUpdate.setText(user.country)
        binding.etPhoneNumberUpdate.setText(user.phoneNumber)
        binding.etEmailUpdate.setText(FirebaseAuth.getInstance().currentUser!!.email)

        topAppBar.setOnMenuItemClickListener { menuItem ->

            when (menuItem.itemId) {
                R.id.settings -> {
                    val intent = Intent(this, SettingsActivity::class.java)
                    intent.putExtra("UserData", user)
                    startActivity(intent)
                    true
                }
                R.id.ab_logout -> {
                    intent = Utility.logout(this@SettingsActivity)
                    startActivity(intent)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
}