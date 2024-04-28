package com.example.parking_places_reservation.core.retrofit

class ParkingRepository(private val endpoint: Endpoint) {
    suspend fun getParkings() = endpoint.getParkings()
}