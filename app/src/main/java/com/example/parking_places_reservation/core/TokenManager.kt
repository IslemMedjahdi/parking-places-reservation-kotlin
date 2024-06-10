package com.example.parking_places_reservation.core

import android.content.Context
import android.content.SharedPreferences

class TokenManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("TokenManager", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun getToken(): String {
        return sharedPreferences.getString("token", "") ?: ""
    }

    fun saveToken(token: String) {
        editor.putString("token", token)
        editor.apply()
    }

    fun clearToken() {
        editor.remove("token")
        editor.apply()
    }



}