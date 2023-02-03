package com.example.engineeringapp.Module

import java.io.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


data class UserData(
    val uid: String?,
    val firstname: String?,
    val lastname: String?,
    val street: String?,
    val zipCode: String?,
    val city: String?,
    val country: String?,
    val phoneNumber: String?
) : Serializable