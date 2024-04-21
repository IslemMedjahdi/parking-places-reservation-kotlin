package com.example.parking_places_reservation.screens.navigation_bar

import com.example.parking_places_reservation.R
import com.example.parking_places_reservation.screens.router.Router

sealed  class BottomNavigationBarItems(var title: String, var icon: Int , var route: String) {
    data object ParkingsList : BottomNavigationBarItems(
        title = "Parkings List",
        icon = R.drawable.baseline_local_parking_24,
        route = Router.ParkingList.route
    )

    data object MyReservations : BottomNavigationBarItems(
        title = "My Reservations",
        icon = R.drawable.baseline_calendar_month_24,
        route = Router.MyReservations.route
    )

    data object Profile : BottomNavigationBarItems(
        title = "Profile",
        icon = R.drawable.baseline_person_24,
        route = Router.Profile.route
    )

    companion object {
        fun values(): List<BottomNavigationBarItems> {
            return listOf(ParkingsList, MyReservations, Profile)
        }
    }
}