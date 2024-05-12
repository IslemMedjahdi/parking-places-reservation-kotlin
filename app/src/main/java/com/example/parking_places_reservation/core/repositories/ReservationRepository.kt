package com.example.parking_places_reservation.core.repositories

import com.example.parking_places_reservation.core.entities.Reservation
import com.example.parking_places_reservation.core.retrofit.Endpoint
import com.example.parking_places_reservation.core.room.ReservationDao
import com.example.parking_places_reservation.core.room.ReservationEntity

class ReservationRepository(private val endpoint: Endpoint, private val ReservationDao: ReservationDao) {
    suspend fun reserve(reserveRequest: Endpoint.ReserveRequest) = endpoint.reserve(reserveRequest)

    fun saveReservationLocal(reservation: ReservationEntity) = ReservationDao.insertReservation(reservation)
}