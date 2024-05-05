package com.example.parking_places_reservation.core.retrofit

import com.example.parking_places_reservation.core.entities.Parking
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Endpoint {
    @GET("/parkings")
    suspend  fun getParkings(): Response<List<Parking>>


    data class RegisterRequest(
        val email: String,
        val password: String,
        val fullName: String,
    )
    @POST("/register")
    suspend fun registerUser(@Body body: RegisterRequest): Response<Unit>

    data class LoginRequest(
        val email: String,
        val password: String,
    )

    data class LoginResponse(
        val token: String,
    )

    @POST("/login")
    suspend fun loginUser(@Body body: LoginRequest): Response<LoginResponse>

    companion object {
        private var endpoint: Endpoint? = null
        fun createEndpoint(): Endpoint {
            if(endpoint ==null) {

                endpoint = Retrofit.Builder().baseUrl("https://662e4f94a7dda1fa378c9df2.mockapi.io/api/").
                            addConverterFactory(GsonConverterFactory.create()).build()
                            .create(Endpoint::class.java)
            }
            return endpoint!!
        }
    }
}