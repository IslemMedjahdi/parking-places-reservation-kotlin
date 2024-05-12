package com.example.parking_places_reservation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.parking_places_reservation.core.entities.Parking
import com.example.parking_places_reservation.core.entities.Reservation
import com.example.parking_places_reservation.core.room.ReservationEntity
import com.example.parking_places_reservation.screens.router.Router
import com.example.parking_places_reservation.`view-models`.AuthViewModel
import com.example.parking_places_reservation.view.ReservationViewModel

@Composable
fun MyReservationsScreen(navController: NavController,authViewModel: AuthViewModel,reservationViewModel : ReservationViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
    ) {
        Box(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "My Reservations List:",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight(900)
            )
        }
        LazyColumn(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(
                items = reservationViewModel.myReservations.value,
                key = { reservation -> reservation.id.toString() }
            ) { reservation ->
                ReservationListItem(reservation, navController = navController)
            }
        }
    }

    LaunchedEffect(key1 = authViewModel.isLoggedIn.value) {
        if(!authViewModel.isLoggedIn.value){
            navController.navigate(Router.Login.createRoute(Router.MyReservations.route))
        }
    }

    LaunchedEffect(key1 = reservationViewModel.myReservations.value) {
        reservationViewModel.getReservationFromLocal()
    }
}

@Composable
fun ReservationListItem(reservation: ReservationEntity , navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(
                    Router.ReservationDetails.createRoute(
                        reservation.id.toString()
                    )
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
                    model = reservation.parkingPhotoUrl,
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
                        text = reservation.parkingName,
                        style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = reservation.parkingAddress,
                        style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = "start time: ${reservation.startTime}",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.Black,
                        ),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = "end time: ${reservation.endTime}",
                        style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
                    )
                }
            }
        }
    }
}