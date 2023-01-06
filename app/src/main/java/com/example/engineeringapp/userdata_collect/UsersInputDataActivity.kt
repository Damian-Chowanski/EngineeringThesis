package com.example.engineeringapp.userdata_collect

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.engineeringapp.MainActivity
import com.example.engineeringapp.Module.UserData
import com.example.engineeringapp.databinding.ActivityUsersInputDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UsersInputDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsersInputDataBinding
    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersInputDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.title = "Present yourself!"
        binding.btnSave.setOnClickListener {

            val name = binding.etName.text.toString()
            val surname = binding.etSurname.text.toString()
            val street = binding.etStreet.text.toString()
            val zipCode = binding.etZipCode.text.toString()
            val city = binding.etCity.text.toString()
            val country = binding.etCountry.text.toString()
            val phoneNumber = binding.etPhoneNumber.text.toString()
            when {
                name.isEmpty() -> {
                    Toast.makeText(
                        this@UsersInputDataActivity,
                        "Please enter a Name",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                surname.isEmpty() -> {
                    Toast.makeText(
                        this@UsersInputDataActivity,
                        "Please enter a Surname",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                street.isEmpty() -> {
                    Toast.makeText(
                        this@UsersInputDataActivity,
                        "Please enter a Street",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                zipCode.isEmpty() -> {
                    Toast.makeText(
                        this@UsersInputDataActivity,
                        "Please enter a Zip code",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                city.isEmpty() -> {
                    Toast.makeText(
                        this@UsersInputDataActivity,
                        "Please enter a City",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                country.isEmpty() -> {
                    Toast.makeText(
                        this@UsersInputDataActivity,
                        "Please enter a Country",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                phoneNumber.isEmpty() -> {
                    Toast.makeText(
                        this@UsersInputDataActivity,
                        "Please enter a Phone number",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    database = FirebaseDatabase.getInstance().getReference("Users")
                    val User =
                        UserData(name, surname, street, zipCode, city, country, phoneNumber)
                    database.child(phoneNumber).setValue(User).addOnSuccessListener {

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
}