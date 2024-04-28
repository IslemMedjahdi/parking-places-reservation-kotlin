package com.example.parking_places_reservation.core.entities

data class Parking(
    val id: String,
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val totalPlaces: Int,
    val freePlaces: Int,
    val price: Double,
    val rating: Double,
    val photoUrl: String,
    val description: String
)

fun getFakeParkings(): List<Parking> {
    return listOf(
        Parking(
            id = "1",
            name = "Parking 1",
            address = "Address 1",
            latitude = 0.0,
            longitude = 0.0,
            totalPlaces = 10,
            freePlaces = 5,
            price = 10.0,
            rating = 4.5,
            photoUrl = "https://picsum.photos/300/200",
            description = "Description 1"
        ),
        Parking(
            id = "2",
            name = "Parking 2",
            address = "Address 2",
            latitude = 0.0,
            longitude = 0.0,
            totalPlaces = 20,
            freePlaces = 10,
            price = 15.0,
            rating = 4.0,
            photoUrl = "https://picsum.photos/300/200",
            description = "Description 2"
        ),
        Parking(
            id = "3",
            name = "Parking 3",
            address = "Address 3",
            latitude = 0.0,
            longitude = 0.0,
            totalPlaces = 30,
            freePlaces = 15,
            price = 20.0,
            rating = 3.5,
            photoUrl = "https://picsum.photos/300/200",
            description = "Description 3"
        ),
        Parking(
            id = "4",
            name = "Parking 4",
            address = "Address 4",
            latitude = 0.0,
            longitude = 0.0,
            totalPlaces = 40,
            freePlaces = 20,
            price = 25.0,
            rating = 3.0,
            photoUrl = "https://picsum.photos/300/200",
            description = "Description 4"
        ),
        Parking(
            id = "5",
            name = "Parking 5",
            address = "Address 5",
            latitude = 0.0,
            longitude = 0.0,
            totalPlaces = 50,
            freePlaces = 25,
            price = 30.0,
            rating = 2.5,
            photoUrl = "https://picsum.photos/300/200",
            description = "Description 5"
        ),
        Parking(
            id = "6",
            name = "Parking 6",
            address = "Address 6",
            latitude = 0.0,
            longitude = 0.0,
            totalPlaces = 60,
            freePlaces = 30,
            price = 35.0,
            rating = 2.0,
            photoUrl = "https://picsum.photos/300/200",
            description = "Description 6"
        ),
        Parking(
            id = "7",
            name = "Parking 7",
            address = "Address 7",
            latitude = 0.0,
            longitude = 0.0,
            totalPlaces = 70,
            freePlaces = 35,
            price = 40.0,
            rating = 1.5,
            photoUrl = "https://picsum.photos/300/200",
            description = "Description 7"
        ),
        Parking(
            id = "8",
            name = "Parking 8",
            address = "Address 8",
            latitude = 0.0,
            longitude = 0.0,
            totalPlaces = 80,
            freePlaces = 40,
            price = 45.0,
            rating = 1.0,
            photoUrl = "https://picsum.photos/300/200",
            description = "Description 8"
        )
    )
}