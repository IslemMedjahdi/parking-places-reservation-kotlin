package com.example.parking_places_reservation

import android.app.Application
import com.example.parking_places_reservation.core.IdManager
import com.example.parking_places_reservation.core.TokenManager
import com.example.parking_places_reservation.core.repositories.AuthRepository
import com.example.parking_places_reservation.core.retrofit.Endpoint
import com.example.parking_places_reservation.core.repositories.ParkingRepository
import com.example.parking_places_reservation.core.repositories.ReservationRepository
import com.example.parking_places_reservation.core.room.ParkingPlacesReservationDatabase

class ParkingReservationApplication: Application() {
    private val endpoint by lazy { Endpoint.createEndpoint() }
    val parkingRepository by lazy { ParkingRepository(endpoint) }

    val authRepository by lazy { AuthRepository(endpoint, TokenManager(applicationContext) , IdManager(applicationContext)) }

    private val database by lazy { ParkingPlacesReservationDatabase.getInstance(this) }
    private val reservationDao by lazy {database.getReservationDao()}

    val reservationRepository by lazy { ReservationRepository(endpoint,reservationDao)  }
}