package com.example.parking_places_reservation.core.retrofit

class AuthRepository(private val endpoint: Endpoint) {
    suspend fun register(body: Endpoint.RegisterRequest) = endpoint.registerUser(body)

    suspend fun login(body: Endpoint.LoginRequest) = endpoint.loginUser(body)
}