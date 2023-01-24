package com.example.engineeringapp.userdata_collect

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.engineeringapp.MainActivity
import com.example.engineeringapp.Module.UserData
import com.example.engineeringapp.databinding.ActivityUsersInputDataBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UsersInputDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsersInputDataBinding
    lateinit var dbReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersInputDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.title = "Present yourself!"

        dbReference = FirebaseDatabase.getInstance().getReference("Users")

        binding.btnSave.setOnClickListener {

            val firstName = binding.etFirstname.text.toString()
            val lastName = binding.etLastname.text.toString()
            val street = binding.etStreet.text.toString()
            val zipCode = binding.etZipCode.text.toString()
            val city = binding.etCity.text.toString()
            val country = binding.etCountry.text.toString()
            val phoneNumber = binding.etPhoneNumber.text.toString()
            val userId = FirebaseAuth.getInstance().uid

            when {
                firstName.isEmpty() -> {
                    Toast.makeText(
                        this@UsersInputDataActivity,
                        "Please enter a firstname",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                lastName.isEmpty() -> {
                    Toast.makeText(
                        this@UsersInputDataActivity,
                        "Please enter a lastname",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                street.isEmpty() -> {
                    Toast.makeText(
                        this@UsersInputDataActivity,
                        "Please enter a street",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                zipCode.isEmpty() -> {
                    Toast.makeText(
                        this@UsersInputDataActivity,
                        "Please enter a zip code",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                city.isEmpty() -> {
                    Toast.makeText(
                        this@UsersInputDataActivity,
                        "Please enter a city",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                country.isEmpty() -> {
                    Toast.makeText(
                        this@UsersInputDataActivity,
                        "Please enter a country",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                phoneNumber.isEmpty() -> {
                    Toast.makeText(
                        this@UsersInputDataActivity,
                        "Please enter a phone number",
                        Toast.LENGTH_SHORT
                    ).show()
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
                    val intent = Intent(this@UsersInputDataActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            }
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