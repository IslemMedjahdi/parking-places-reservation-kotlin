package com.example.parking_places_reservation.screens

import android.util.DisplayMetrics
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.parking_places_reservation.R
import com.example.parking_places_reservation.`view-models`.ParkingByIdViewModel


@Composable
fun ParkingDetailsByIdScreen(navController: NavController,id: String,parkingByIdModel : ParkingByIdViewModel) {

    parkingByIdModel.getParkingById(id)
    val context = LocalContext.current
    val displayMetrics: DisplayMetrics = context.getResources().getDisplayMetrics()
    val dpHeight = displayMetrics.heightPixels / displayMetrics.density
    val dpWidth = displayMetrics.widthPixels / displayMetrics.density
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ){
        AsyncImage(
            model = parkingByIdModel.selectedParking.value?.photoUrl ?: "",
            contentDescription = null,
            modifier = Modifier
                .size(dpWidth.dp, dpHeight.dp / 3)
                .border(
                    shape = RoundedCornerShape(8.dp),
                    width = 1.dp,
                    color = Color.Gray
                )
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "${parkingByIdModel.selectedParking.value?.name}",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight(900),
            modifier = Modifier.padding(8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ){
           Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(painter = painterResource(id = R.drawable.star), contentDescription ="star"
                    , modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .size(30.dp)
                        .border(1.dp, Color.DarkGray, RoundedCornerShape(8.dp))
                        .background(Color.LightGray)


                    ,
                    tint = Color.Yellow,
                )
                Text(
                    text = "${parkingByIdModel.selectedParking.value?.rating?.toInt()}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight(900),
                    modifier = Modifier.padding(8.dp)

                )



            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(painter = painterResource(id = R.drawable.star), contentDescription ="star"
                    , modifier = Modifier
                        .background(Color.LightGray)
                        .size(40.dp))
                Text(
                    text = "${parkingByIdModel.selectedParking.value?.price?.toInt()}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = MaterialTheme.typography.bodyMedium.fontWeight
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(painter = painterResource(id = R.drawable.star), contentDescription ="star"
                    , modifier = Modifier
                        .background(Color.LightGray)
                        .size(40.dp))
                Text(
                    text = "${parkingByIdModel.selectedParking.value?.freePlaces?.toInt()}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = MaterialTheme.typography.bodyMedium.fontWeight
                )
            }


        }
        Text(
            text = "${parkingByIdModel.selectedParking.value?.description}",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = MaterialTheme.typography.bodyMedium.fontWeight
        )

    }
}


