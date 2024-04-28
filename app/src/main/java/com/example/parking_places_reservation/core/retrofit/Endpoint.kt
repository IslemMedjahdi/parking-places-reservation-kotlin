package com.example.parking_places_reservation.core.retrofit

import com.example.parking_places_reservation.core.entities.Parking
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Endpoint {
    @GET("/parkings")
    suspend  fun getParkings(): Response<List<Parking>>


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