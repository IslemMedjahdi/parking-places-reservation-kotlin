package com.example.parking_places_reservation.core.retrofit

import com.example.parking_places_reservation.core.entities.Parking
import com.example.parking_places_reservation.core.entities.Reservation
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.POST

interface Endpoint {

    @POST("/device-token")
    suspend fun saveToken(@Body body : tokenPostRequest): Response<Unit>

    @GET("/parkings")
    suspend  fun getParkings(): Response<List<Parking>>

    @GET("/parkings/{id}")
    suspend fun getParkingById(@Path("id") id:String): Response<Parking>


    data class RegisterRequest(
        val email: String,
        val password: String,
        val fullName: String,
    )

    data class tokenPostRequest(
        val token: String
    )

    @POST("/auth/register")
    suspend fun registerUser(@Body body: RegisterRequest): Response<Unit>

    data class LoginRequest(
        val email: String,
        val password: String,
    )

    data class LoginResponse(
        val token: String,
        val id: String
    )

    @POST("/auth/login")
    suspend fun loginUser(@Body body: LoginRequest): Response<LoginResponse>

    data class LoginWithGoogleRequest(
        val tokenId: String
    )

    @POST("/auth/google")
    suspend fun loginWithGoogle(@Body body: LoginWithGoogleRequest): Response<LoginResponse>

    data class ReserveRequest(
        val startDate: String,
        val endDate: String,
        val parkingId: String,
        val driverId : String
    )


    @POST("/reservations")
    suspend fun reserve(@Body body: ReserveRequest): Response<Reservation>

    companion object {
        private var endpoint: Endpoint? = null
        fun createEndpoint(): Endpoint {
            if(endpoint ==null) {
                endpoint = Retrofit.Builder().baseUrl("https://a9df-105-99-137-7.ngrok-free.app/").
                            addConverterFactory(GsonConverterFactory.create()).build()
                            .create(Endpoint::class.java)
            }
            return endpoint!!
        }
    }
}