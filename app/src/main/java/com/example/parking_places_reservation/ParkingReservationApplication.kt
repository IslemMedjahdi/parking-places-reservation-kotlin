package com.example.parking_places_reservation

import android.app.Application
import com.example.parking_places_reservation.core.TokenManager
import com.example.parking_places_reservation.core.retrofit.AuthRepository
import com.example.parking_places_reservation.core.retrofit.Endpoint
import com.example.parking_places_reservation.core.retrofit.ParkingRepository
import com.example.parking_places_reservation.core.retrofit.ReservationRepository

class ParkingReservationApplication: Application() {
    val parkingRepository by lazy { ParkingRepository(Endpoint.createEndpoint()) }
    val authRepository by lazy { AuthRepository(Endpoint.createEndpoint(), TokenManager(applicationContext)) }
    val reservationRepository by lazy { ReservationRepository(Endpoint.createEndpoint()) }

}