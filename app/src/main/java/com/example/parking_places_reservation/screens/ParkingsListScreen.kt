package com.example.parking_places_reservation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.parking_places_reservation.screens.router.Router

@Composable
fun ParkingListScreen(navController: NavController) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        Text(text = "Parkings List Screen", style = MaterialTheme.typography.headlineMedium)
        Button(onClick = {
            navController.navigate(Router.ParkingDetailsById.createRoute("5"))
        }) {
            Text(text = "Go to parking details for id 5")
        }
    }
}