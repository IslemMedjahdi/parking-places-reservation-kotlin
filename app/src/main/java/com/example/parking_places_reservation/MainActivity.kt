package com.example.parking_places_reservation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.parking_places_reservation.screens.AuthScreen
import com.example.parking_places_reservation.screens.CreateReservationScreen
import com.example.parking_places_reservation.screens.MyReservationsScreen
import com.example.parking_places_reservation.screens.ParkingDetailsByIdScreen
import com.example.parking_places_reservation.screens.ParkingListOnMapScreen
import com.example.parking_places_reservation.screens.ParkingListScreen
import com.example.parking_places_reservation.screens.ProfileScreen
import com.example.parking_places_reservation.screens.ReservationDetailsScreen
import com.example.parking_places_reservation.screens.navigation_bar.BottomNavigationBarItems
import com.example.parking_places_reservation.screens.router.Router
import com.example.parking_places_reservation.ui.theme.ParkingplacesreservationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ParkingplacesreservationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navController = rememberNavController();
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(navController = navController)
                        }
                    ) {
                        Column(modifier = Modifier.padding(it)) {
                            NavigationAppHost(
                                navController = navController,
                                startRoute = Router.ParkingList.route
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NavigationAppHost(navController: NavHostController, startRoute: String) {
    NavHost(navController = navController, startDestination = startRoute) {
        composable(Router.Auth.route) {
            AuthScreen(navController = navController)
        }
        composable(Router.ParkingList.route) {
            ParkingListScreen(navController = navController)
        }
        composable(Router.ParkingDetailsById.route) { navBackStackEntry ->
            val parkingId = navBackStackEntry.arguments?.getString("parkingId")
            ParkingDetailsByIdScreen(navController = navController,id = parkingId ?: "")
        }
        composable(Router.MyReservations.route) {
            MyReservationsScreen(navController = navController)
        }
        composable(Router.Profile.route) {
            ProfileScreen(navController = navController)
        }
        composable(Router.ParkingListOnMap.route) {
            ParkingListOnMapScreen(navController = navController)
        }
        composable(Router.CreateReservation.route) { navBackStackEntry ->
            val parkingId = navBackStackEntry.arguments?.getString("parkingId")
            CreateReservationScreen(navController = navController,parkingId = parkingId ?: "")
        }
        composable(Router.ReservationDetails.route){navBackStackEntry ->
            val reservationId = navBackStackEntry.arguments?.getString("reservationId")
            ReservationDetailsScreen(navController = navController,reservationId = reservationId ?: "")
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState();
    val currentRoute = navBackStackEntry?.destination?.route;
    BottomNavigation {
        BottomNavigationBarItems.values().forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(item.icon),
                        contentDescription = item.title,
                        tint = if (currentRoute == item.route) Color.White else Color.Gray,
                    )
                },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route)
                }
            )
        }
    }
}