package com.example.engineeringapp.menuItems

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.engineeringapp.MainActivity
import com.example.engineeringapp.Module.UserData
import com.example.engineeringapp.Module.Utility
import com.example.engineeringapp.R
import com.example.engineeringapp.databinding.ActivitySettingsBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    lateinit var topAppBar: MaterialToolbar
    lateinit var dbReference: DatabaseReference

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

        dbReference = FirebaseDatabase.getInstance().getReference("Users")

        binding.btnSave.setOnClickListener{

            val userId = user.uid
            val firstName = binding.etFirstnameUpdate.text.toString()
            val lastName = binding.etLastnameUpdate.text.toString()
            val street = binding.etStreetUpdate.text.toString()
            val zipCode = binding.etZipCodeUpdate.text.toString()
            val city = binding.etCityUpdate.text.toString()
            val country = binding.etCountryUpdate.text.toString()
            val phoneNumber = binding.etPhoneNumberUpdate.text.toString()
            val email = binding.etEmailUpdate.text.toString()


            when {
                firstName.isEmpty() -> {
                    Toast.makeText(
                        this,
                        "Please enter a firstname",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                lastName.isEmpty() -> {
                    Toast.makeText(
                        this,
                        "Please enter a lastname",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                street.isEmpty() -> {
                    Toast.makeText(
                        this,
                        "Please enter a street",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                zipCode.isEmpty() -> {
                    Toast.makeText(
                        this,
                        "Please enter a zip code",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                city.isEmpty() -> {
                    Toast.makeText(
                        this,
                        "Please enter a city",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                country.isEmpty() -> {
                    Toast.makeText(
                        this,
                        "Please enter a country",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                phoneNumber.isEmpty() -> {
                    Toast.makeText(
                        this,
                        "Please enter a phone number",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                email.isEmpty() -> {
                    Toast.makeText(
                        this,
                        "Please enter an email",
                        Toast.LENGTH_SHORT
                    )
                }
                else -> {

                    val user =
                        UserData(userId, firstName, lastName, street, zipCode, city, country, phoneNumber)
                    dbReference.child(userId.toString()).setValue(user)
                        .addOnSuccessListener {

                            Toast.makeText(this, "Successfully saved", Toast.LENGTH_SHORT).show()

                        }.addOnFailureListener {
                            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()

                        }
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }

        }

        topAppBar.setOnMenuItemClickListener { menuItem ->

            when (menuItem.itemId) {
                R.id.settings -> {
                    closeContextMenu()
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