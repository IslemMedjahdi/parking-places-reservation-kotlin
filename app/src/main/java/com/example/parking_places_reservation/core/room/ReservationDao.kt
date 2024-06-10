package com.example.parking_places_reservation.core.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ReservationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertReservation(reservation: ReservationEntity)

    @Query("SELECT * FROM RESERVATIONS WHERE user_id = :userId")
    fun getAllReservationsOfUser(userId : String): List<ReservationEntity>

    @Query("SELECT * FROM RESERVATIONS WHERE id = :id")
    fun getReservationById(id: String): ReservationEntity

    @Query("DELETE FROM RESERVATIONS")
    fun deleteAllReservations()
}