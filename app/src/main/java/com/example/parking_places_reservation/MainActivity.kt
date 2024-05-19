package com.example.parking_places_reservation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.parking_places_reservation.`view-models`.AuthViewModel
import com.example.parking_places_reservation.`view-models`.ParkingViewModel
import com.example.parking_places_reservation.`view-models`.RegisterViewModel
import com.example.parking_places_reservation.screens.CreateReservationScreen
import com.example.parking_places_reservation.screens.LoginScreen
import com.example.parking_places_reservation.screens.MyReservationsScreen
import com.example.parking_places_reservation.screens.ParkingDetailsByIdScreen
import com.example.parking_places_reservation.screens.ParkingListOnMapScreen
import com.example.parking_places_reservation.screens.ParkingListScreen
import com.example.parking_places_reservation.screens.ParkingOnMapScreen
import com.example.parking_places_reservation.screens.ProfileScreen
import com.example.parking_places_reservation.screens.RegisterScreen
import com.example.parking_places_reservation.screens.ReservationDetailsScreen
import com.example.parking_places_reservation.screens.navigation_bar.BottomNavigationBarItems
import com.example.parking_places_reservation.screens.router.Router
import com.example.parking_places_reservation.ui.theme.ParkingplacesreservationTheme
import com.example.parking_places_reservation.view.ReservationViewModel

class MainActivity : ComponentActivity() {
    private val parkingsViewModel: ParkingViewModel by viewModels {
        ParkingViewModel.Factory(
            (application as ParkingReservationApplication).parkingRepository
        )
    }

    private val registerViewModel: RegisterViewModel by viewModels {
        RegisterViewModel.Factory(
            (application as ParkingReservationApplication).authRepository
        )
    }
    private val authViewModel: AuthViewModel by viewModels {
        AuthViewModel.Factory(
            (application as ParkingReservationApplication).authRepository
        )
    }
    private val reservationViewModel: ReservationViewModel by viewModels {
        ReservationViewModel.Factory(
            (application as ParkingReservationApplication).reservationRepository
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ParkingplacesreservationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navController = rememberNavController()
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(navController = navController)
                        }
                    ) {
                        Column(modifier = Modifier.padding(it)) {
                            NavigationAppHost(
                                navController = navController,
                                startRoute = Router.ParkingList.route,
                                parkingsViewModel = parkingsViewModel,
                                registerViewModel = registerViewModel,
                                authViewModel = authViewModel,
                                reservationViewModel = reservationViewModel


                            )
                        }
                    }
                }
                LaunchedEffect(key1 = true) {
                    authViewModel.setup()
                }
            }
        }
    }

}

@Composable
fun NavigationAppHost(navController: NavHostController, startRoute: String, parkingsViewModel: ParkingViewModel, registerViewModel: RegisterViewModel, authViewModel: AuthViewModel, reservationViewModel: ReservationViewModel) {

    NavHost(navController = navController, startDestination = startRoute) {
        composable(Router.Login.route) {navBackStackEntry ->
            val redirectRoute =navBackStackEntry.arguments?.getString("redirect")
            LoginScreen(navController = navController,authViewModel = authViewModel,redirectRoute)
        }
        composable(Router.Register.route){
            RegisterScreen(navController = navController,registerViewModel = registerViewModel,authViewModel = authViewModel)
        }
        composable(Router.ParkingList.route) {
            ParkingListScreen(navController = navController,parkingsModel = parkingsViewModel)
        }
        composable(Router.ParkingDetailsById.route) { navBackStackEntry ->
            val parkingId = navBackStackEntry.arguments?.getString("parkingId")
            ParkingDetailsByIdScreen(navController = navController,id = parkingId ?: "",parkingViewModel=parkingsViewModel)
        }
        composable(Router.ParkingOnMap.route) { navBackStackEntry ->
            val parkingId = navBackStackEntry.arguments?.getString("parkingId")
            ParkingOnMapScreen(navController = navController,id = parkingId ?: "",parkingsModel=parkingsViewModel)
        }
        composable(Router.MyReservations.route) {
            MyReservationsScreen(navController = navController,authViewModel, reservationViewModel)
        }
        composable(Router.Profile.route) {
            ProfileScreen(navController = navController,authViewModel = authViewModel)
        }
        composable(Router.ParkingListOnMap.route) {
            ParkingListOnMapScreen(navController = navController,parkingsViewModel)
        }
        composable(Router.CreateReservation.route) { navBackStackEntry ->
            val parkingId = navBackStackEntry.arguments?.getString("parkingId")
            CreateReservationScreen(navController = navController,parkingId = parkingId ?: "", reservationViewModel = reservationViewModel,authViewModel = authViewModel)
        }
        composable(Router.ReservationDetails.route){navBackStackEntry ->
            val reservationId = navBackStackEntry.arguments?.getString("reservationId")
            ReservationDetailsScreen(navController = navController,reservationId = reservationId ?: "",authViewModel=authViewModel , reservationViewModel = reservationViewModel , parkingViewModel = parkingsViewModel)
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    BottomNavigation (

    ) {
        BottomNavigationBarItems.values().forEach { item ->
            BottomNavigationItem(
                modifier = Modifier.background(MaterialTheme.colorScheme.primary),
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