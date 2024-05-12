package com.example.parking_places_reservation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.parking_places_reservation.screens.router.Router
import com.example.parking_places_reservation.`view-models`.AuthViewModel

@Composable
fun ReservationDetailsScreen(navController: NavController,reservationId: String,authViewModel: AuthViewModel) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        Text(text = "Showing Create reservation for $reservationId", style = MaterialTheme.typography.headlineMedium)
    }
    LaunchedEffect(key1 = authViewModel.isLoggedIn.value) {
        if(!authViewModel.isLoggedIn.value){
            navController.navigate(Router.Login.createRoute(Router.ReservationDetails.createRoute(reservationId)))
        }
    }
}