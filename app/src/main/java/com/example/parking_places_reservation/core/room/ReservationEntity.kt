package com.example.parking_places_reservation.core.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="reservations" )
data class ReservationEntity(
    @PrimaryKey(
        autoGenerate = false
    )
    var id: String,

    @ColumnInfo(name = "user_id")
    var userId: String,

    @ColumnInfo(name = "parking_id")
    var parkingId: String,

    @ColumnInfo(name = "parking_name")
    var parkingName: String,

    @ColumnInfo(name = "parking_photo_url")
    var parkingPhotoUrl: String,

    @ColumnInfo(name = "parking_address")
    var parkingAddress: String,

    @ColumnInfo(name = "start_time")
    var startTime: String,

    @ColumnInfo(name = "end_time")
    var endTime: String,
)