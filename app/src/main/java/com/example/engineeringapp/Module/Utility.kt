package com.example.engineeringapp.Module

import android.content.Context
import android.content.Intent
import com.example.engineeringapp.UI_login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class Utility {
    companion object {
        fun logout(context: Context): Intent {
            FirebaseAuth.getInstance().signOut()
            return Intent(context, LoginActivity::class.java)
        }
    }
}