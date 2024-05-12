package com.example.parking_places_reservation.core.entities

data class Reservation (
    val id: String,
    val startDate: String,
    val endDate: String,
    val parkingId: String,
    val parkingName: String,
    val parkingPhotoUrl: String,
    val parkingAddress: String
)