package com.example.engineeringapp.Module

import java.io.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


data class UserData(
    val uid: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val street: String? = null,
    val zipCode: String? = null,
    val city: String? = null,
    val country: String? = null,
    val phoneNumber: String? = null
) : Serializable