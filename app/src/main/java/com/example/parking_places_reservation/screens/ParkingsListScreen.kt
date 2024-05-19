package com.example.parking_places_reservation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.parking_places_reservation.R
import com.example.parking_places_reservation.core.entities.Parking
import com.example.parking_places_reservation.`view-models`.ParkingViewModel
import com.example.parking_places_reservation.screens.router.Router


@Composable
fun ParkingListScreen(navController: NavController,parkingsModel: ParkingViewModel) {
   LaunchedEffect(Unit) {
       parkingsModel.getParkings()
   }
   
    parkingsModel.loading.value.let {
        LoadingIndicator(it)
    }
    
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
    ) {
        Row(modifier = Modifier.padding(8.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Parkings List:",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = MaterialTheme.typography.titleLarge.fontWeight
            )
            Button(onClick = {
                navController.navigate(Router.ParkingListOnMap.route)
            }) {
                    Text(text = "See On Map")
            }
        }
        LazyColumn(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(parkingsModel.parkings.value) { parking ->
                ParkingListItem(parking, navController)
            }
        }
    }

    LaunchedEffect(true) {
        if(parkingsModel.parkings.value.isEmpty()){
            parkingsModel.getParkings()
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
            }
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary,
                                Color(0xFFFFFFFF)
                            )
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = parking.photoUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .padding(8.dp)
                        .border(
                            shape = RoundedCornerShape(8.dp),
                            width = 1.dp,
                            color = Color.Gray
                        )
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop,
                )
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = parking.name,
                        style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = parking.address,
                        style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = "${parking.price} DZD",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = "Free places: ${parking.freePlaces}",
                        style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
                    )
                }
            }
        }
    }
}

@Composable
fun LoadingIndicator(show: Boolean){
    if(show){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
    }
}

