package com.example.parking_places_reservation.screens.router

sealed  class Router(val route: String) {
    data object Login : Router("login?redirect={redirect}") {
        fun createRoute(redirect: String?): String {
            return "login?redirect=$redirect"
        }
    }
    data object Register: Router("register")
    data object Profile: Router("profile")
    data object ParkingList: Router("parkings")
    data object ParkingListOnMap: Router("parkings_map")
    data object ParkingDetailsById: Router("parkings/{parkingId}") {
        fun createRoute(parkingId: String): String {
            return "parkings/$parkingId"
        }
    }
    data object MyReservations: Router("my_reservations")
    data object CreateReservation: Router("create_reservation/{parkingId}") {
        fun createRoute(parkingId: String): String {
            return "create_reservation/$parkingId"
        }
    }
    data object ReservationDetails: Router("reservations/{reservationId}") {
        fun createRoute(reservationId: String): String {
            return "reservations/$reservationId"
        }
    }
}