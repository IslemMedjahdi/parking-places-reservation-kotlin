package com.example.parking_places_reservation.core.retrofit

class ReservationRepository(private val endpoint: Endpoint) {
    suspend fun reserve(reserveRequest: Endpoint.ReserveRequest) = endpoint.reserve(reserveRequest)
}