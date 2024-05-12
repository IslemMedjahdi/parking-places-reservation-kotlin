package com.example.parking_places_reservation.core.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ReservationEntity::class], version = 1)
abstract class ParkingPlacesReservationDatabase: RoomDatabase() {
    abstract fun getReservationDao():ReservationDao

    companion object {
        private var INSTANCE: ParkingPlacesReservationDatabase? = null
        fun getInstance(context: Context): ParkingPlacesReservationDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance =
                        Room.databaseBuilder(context,ParkingPlacesReservationDatabase::class.java,
                            "parking_database").build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }

}