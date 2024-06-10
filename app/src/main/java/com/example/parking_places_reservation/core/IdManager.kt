package com.example.parking_places_reservation.core

import android.content.Context
import android.content.SharedPreferences

class IdManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("IdManager", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

     fun getId(): String {
        return sharedPreferences.getString("id", "") ?: ""
    }

     fun saveId(id: String) {
        editor.putString("id", id)
        editor.apply()
    }

     fun clearId() {
        editor.remove("id")
        editor.apply()
    }


}