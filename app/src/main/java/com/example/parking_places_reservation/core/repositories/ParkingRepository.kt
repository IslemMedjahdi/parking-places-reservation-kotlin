package com.example.parking_places_reservation.core.repositories

import com.example.parking_places_reservation.core.retrofit.Endpoint

class ParkingRepository(private val endpoint: Endpoint) {
    suspend fun getParkings() = endpoint.getParkings()
    suspend fun getParkingById(id: String) = endpoint.getParkingById(id)
}