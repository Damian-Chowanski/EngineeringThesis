package com.example.engineeringapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.engineeringapp.menuItems.SettingsActivity
import com.example.engineeringapp.Module.UserData
import com.example.engineeringapp.Module.Utility
import com.example.engineeringapp.databinding.ActivityMainBinding
import com.example.engineeringapp.menuItems.ReportNewCase
import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var topAppBar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN // Hide action bar
        topAppBar = findViewById(R.id.topAppBar)

        val userId = FirebaseAuth.getInstance().uid.toString()


        binding.userId.text = "User_ID :: $userId"

        val emailId = FirebaseAuth.getInstance().currentUser?.email

        binding.userEmail.text = "User email :: $emailId"

        val btnLogout: Button = findViewById(R.id.btn_logout)
        btnLogout.setOnClickListener {
            val intent = Utility.logout(this@MainActivity)
            startActivity(intent)
        }

        setTopAppBarMenu()
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
                    val intent = Intent(this, SettingsActivity::class.java)
                    //intent.putExtra("UserData", user)
                    startActivity(intent)
                    true
                }
                R.id.ab_logout -> {
                    val intent = Utility.logout(this@MainActivity)
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
