package com.example.engineeringapp.Module

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.engineeringapp.UI_login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class Utility {
    companion object {
        fun logout(context: Context): Intent {
            FirebaseAuth.getInstance().signOut()
            return Intent(context, LoginActivity::class.java)
        }
    }
}