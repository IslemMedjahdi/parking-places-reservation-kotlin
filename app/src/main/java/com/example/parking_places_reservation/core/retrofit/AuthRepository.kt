package com.example.parking_places_reservation.core.retrofit

import com.example.parking_places_reservation.core.TokenManager

class AuthRepository(private val endpoint: Endpoint,private val tokenManager: TokenManager) {
    suspend fun register(body: Endpoint.RegisterRequest) = endpoint.registerUser(body)

    suspend fun login(body: Endpoint.LoginRequest) = endpoint.loginUser(body)

    fun getToken() = tokenManager.getToken()

    fun saveToken(token: String) = tokenManager.saveToken(token)
}