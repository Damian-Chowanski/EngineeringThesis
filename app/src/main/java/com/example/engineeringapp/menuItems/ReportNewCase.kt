package com.example.engineeringapp.menuItems

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import com.example.engineeringapp.Module.Utility
import com.example.engineeringapp.R
import com.example.engineeringapp.databinding.ActivityLoginBinding
import com.example.engineeringapp.databinding.ActivityReportNewCaseBinding
import com.google.android.material.appbar.MaterialToolbar

class ReportNewCase : AppCompatActivity() {
    private lateinit var binding: ActivityReportNewCaseBinding
    lateinit var topAppBar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportNewCaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN // Hide action bar
        topAppBar = findViewById(R.id.topAppBar)

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
                    closeContextMenu()
                    true
                }
                R.id.ab_settings -> {
                    val intent = Intent(this, SettingsActivity::class.java)
                    //intent.putExtra("UserData", user)
                    startActivity(intent)
                    true
                }
                R.id.ab_logout -> {
                    val intent = Utility.logout(this)
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