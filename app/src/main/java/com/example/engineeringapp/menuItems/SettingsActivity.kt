package com.example.engineeringapp.menuItems

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.engineeringapp.Module.UserData
import com.example.engineeringapp.Module.Utility
import com.example.engineeringapp.R
import com.example.engineeringapp.databinding.ActivitySettingsBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private val userId = FirebaseAuth.getInstance().uid.toString()
    lateinit var topAppBar: MaterialToolbar
    lateinit var dbref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN // Hide action bar
        topAppBar = findViewById(R.id.topAppBar)

        downloadData()

        binding.btnSave.setOnClickListener { updateData()}
        setTopAppBarMenu()
    }

    private fun downloadData() {
        dbref = FirebaseDatabase.getInstance().getReference("Users").child(userId)
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.apply{
                    etFirstnameUpdate.setText(snapshot.child("firstname").value.toString())
                    etLastnameUpdate.setText(snapshot.child("lastname").value.toString())
                    etStreetUpdate.setText(snapshot.child("street").value.toString())
                    etZipCodeUpdate.setText(snapshot.child("zipCode").value.toString())
                    etCityUpdate.setText(snapshot.child("city").value.toString())
                    etCountryUpdate.setText(snapshot.child("country").value.toString())
                    etPhoneNumberUpdate.setText(snapshot.child("phoneNumber").value.toString())
                    etEmailUpdate.setText(FirebaseAuth.getInstance().currentUser!!.email)
                }
                dataVisibility(true)
            }
            override fun onCancelled(error: DatabaseError) {
                //Error
            }
        })
    }

    private fun dataVisibility(bool: Boolean) {
        binding.tvUpdate.isVisible = bool
        binding.tvCity.isVisible = bool
        binding.tvCountry.isVisible = bool
        binding.tvEmail.isVisible = bool
        binding.tvName.isVisible = bool
        binding.tvLastname.isVisible = bool
        binding.tvPhoneNumber.isVisible = bool
        binding.tvStreet.isVisible = bool
        binding.tvZipCode.isVisible = bool
        binding.etFirstnameUpdate.isVisible = bool
        binding.etCityUpdate.isVisible = bool
        binding.etEmailUpdate.isVisible = bool
        binding.etCountryUpdate.isVisible = bool
        binding.etLastnameUpdate.isVisible = bool
        binding.etPhoneNumberUpdate.isVisible = bool
        binding.etStreetUpdate.isVisible = bool
        binding.etZipCodeUpdate.isVisible = bool
        binding.btnSave.isVisible = bool
        binding.status.isVisible = !bool
    }

    private fun updateData() {
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
                    UserData(
                        userId,
                        firstName,
                        lastName,
                        street,
                        zipCode,
                        city,
                        country,
                        phoneNumber
                    )
                dbref.setValue(user)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Successfully saved", Toast.LENGTH_SHORT).show()

                    }.addOnFailureListener {
                        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()

                    }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.top_app_bar_menu, menu)
        return true
    }

    private fun setTopAppBarMenu() {
        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.ab_new_case -> {
                    val intent = Intent(this, ReportNewCase::class.java)
                    startActivity(intent)
                    true
                }
                R.id.ab_settings -> {
                    closeContextMenu()
                    true
                }
                R.id.ab_logout -> {
                    val intent = Utility.logout(this@SettingsActivity)
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