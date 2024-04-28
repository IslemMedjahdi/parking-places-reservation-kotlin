package com.example.parking_places_reservation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.parking_places_reservation.core.entities.Parking
import com.example.parking_places_reservation.core.entities.getFakeParkings
import com.example.parking_places_reservation.models.ParkingModel
import com.example.parking_places_reservation.screens.router.Router

@Composable
fun ParkingListScreen(navController: NavController,parkingsModel: ParkingModel) {

    parkingsModel.getParkings();

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
    ) {
        Box(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "Parkings List:",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = MaterialTheme.typography.titleLarge.fontWeight
            )
        }
        LazyColumn(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(parkingsModel.parkings.value) { parking ->
                ParkingListItem(parking, navController)
            }
        }
    }
}

@Composable
fun ParkingListItem(parking: Parking, navController: NavController) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate(
                        Router.ParkingDetailsById.createRoute(parking.id)
                    )
                },
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top,
            ) {
                AsyncImage(
                    model = parking.photoUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .width(150.dp)
                        .height(100.dp),
                    contentScale = ContentScale.Crop,
                )
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(text = parking.name, style = MaterialTheme.typography.titleLarge)
                    Text(
                        text = parking.address,
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Gray
                    )
                    Text(
                        text = "${parking.price} DZD",
                        fontWeight = MaterialTheme.typography.titleMedium.fontWeight
                    )
                    Text(text = "Free places: ${parking.freePlaces}")
                }
            }
    }
}
