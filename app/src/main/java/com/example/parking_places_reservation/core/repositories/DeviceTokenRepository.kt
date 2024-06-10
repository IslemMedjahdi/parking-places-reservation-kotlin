package com.example.parking_places_reservation.core.repositories

import com.example.parking_places_reservation.core.retrofit.Endpoint

class DeviceTokenRepository (private val endpoint: Endpoint) {
    suspend fun saveToken(body : Endpoint.tokenPostRequest) = endpoint.saveToken(body)

}