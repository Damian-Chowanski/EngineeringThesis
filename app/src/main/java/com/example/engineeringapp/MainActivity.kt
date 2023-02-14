package com.example.engineeringapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.engineeringapp.menuItems.SettingsActivity
import com.example.engineeringapp.Module.UserData
import com.example.engineeringapp.Module.Utility
import com.example.engineeringapp.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var topAppBar: MaterialToolbar
    private lateinit var dbref : DatabaseReference
    private lateinit var user : UserData

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        topAppBar = findViewById(R.id.topAppBar)
        val userId = FirebaseAuth.getInstance().uid.toString()

        //getting data for logged user
        dbref = FirebaseDatabase.getInstance().getReference("Users").child(userId)
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val id = userId
                val firstname = snapshot.child("firstname").value.toString()
                val lastname = snapshot.child("lastname").value.toString()
                val street = snapshot.child("street").value.toString()
                val zipCode = snapshot.child("zipCode").value.toString()
                val city = snapshot.child("city").value.toString()
                val country = snapshot.child("country").value.toString()
                val phone = snapshot.child("phoneNumber").value.toString()
                user = UserData(id, firstname, lastname, street, zipCode, city, country, phone)
            }
            override fun onCancelled(error: DatabaseError) {
                println("ERROR")
            }
        })

        binding.userId.text = "User_ID :: $userId"

        val emailId = FirebaseAuth.getInstance().currentUser!!.email

        binding.userEmail.text = "User email :: $emailId"

        val btnLogout: Button = findViewById(R.id.btn_logout)
        btnLogout.setOnClickListener {
            intent = Utility.logout(this@MainActivity)
            startActivity(intent)
        }

        topAppBar.setOnMenuItemClickListener { menuItem ->

            when (menuItem.itemId) {
                R.id.settings -> {
                    val intent = Intent(this, SettingsActivity::class.java)
                    intent.putExtra("UserData", user)
                    startActivity(intent)
                    true
                }
                R.id.ab_logout -> {
                    intent = Utility.logout(this@MainActivity)
                    startActivity(intent)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }


    //hide action bar
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            this.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN)
        }
    }
}

