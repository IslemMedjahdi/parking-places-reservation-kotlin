package com.example.parking_places_reservation.core.repositories

import com.example.parking_places_reservation.core.IdManager
import com.example.parking_places_reservation.core.TokenManager
import com.example.parking_places_reservation.core.retrofit.Endpoint

class AuthRepository(private val endpoint: Endpoint, private val tokenManager: TokenManager , private val IdManager: IdManager) {
    suspend fun register(body: Endpoint.RegisterRequest) = endpoint.registerUser(body)

    suspend fun login(body: Endpoint.LoginRequest) = endpoint.loginUser(body)

    suspend fun loginWithGoogle(body: Endpoint.LoginWithGoogleRequest) = endpoint.loginWithGoogle(body)

    fun getToken() = tokenManager.getToken()

    fun saveToken(token: String) = tokenManager.saveToken(token)

    fun clearToken() = tokenManager.clearToken()

    fun saveId(id: String) = IdManager.saveId(id)

    fun getId() = IdManager.getId()

    fun clearId() = IdManager.clearId()


}