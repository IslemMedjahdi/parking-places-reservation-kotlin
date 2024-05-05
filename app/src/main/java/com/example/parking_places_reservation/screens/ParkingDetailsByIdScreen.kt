package com.example.parking_places_reservation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.parking_places_reservation.models.ParkingByIdViewModel

@Composable
fun ParkingDetailsByIdScreen(navController: NavController,id: String,parkingByIdModel : ParkingByIdViewModel) {

    parkingByIdModel.getParkingById(id)
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        Text(
            text = "Parking Details:",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = MaterialTheme.typography.titleLarge.fontWeight
        )
        parkingByIdModel.selectedParking.value?.let {
            Text(
                text = "Name: ${it.name}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = MaterialTheme.typography.bodyMedium.fontWeight
            )
            Text(
                text = "Address: ${it.address}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = MaterialTheme.typography.bodyMedium.fontWeight
            )
            Text(
                text = "Price: ${it.price}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = MaterialTheme.typography.bodyMedium.fontWeight
            )
            Text(
                text = "Rating: ${it.rating}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = MaterialTheme.typography.bodyMedium.fontWeight
            )
        }
    }
}