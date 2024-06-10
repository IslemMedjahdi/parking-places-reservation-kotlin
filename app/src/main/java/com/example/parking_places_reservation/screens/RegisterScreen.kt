package com.example.parking_places_reservation.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.parking_places_reservation.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.parking_places_reservation.`view-models`.RegisterViewModel
import com.example.parking_places_reservation.screens.router.Router
import com.example.parking_places_reservation.`view-models`.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(navController: NavController,registerViewModel: RegisterViewModel,authViewModel: AuthViewModel){
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 30.dp, end = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 20.dp)
        )
        Text(
            modifier = Modifier.padding(bottom = 10.dp),
            text = "Create Account",
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp,
        )
        Text(
            text = "Enter your credentials to register",
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp, color = Color.Gray,
            )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = registerViewModel.fullName.value,
            leadingIcon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "emailIcon") },
            onValueChange = {
                registerViewModel.fullName.value = it
            },
            label = { Text(text = "Full Name") },
            placeholder = { Text(text = "Enter your full Name") },
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = registerViewModel.email.value,
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon") },
            onValueChange = {
                registerViewModel.email.value = it
            },
            label = { Text(text = "Email address") },
            placeholder = { Text(text = "Enter your e-mail") },
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = registerViewModel.password.value,
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "LockIcon") },
            onValueChange = {
                registerViewModel.password.value = it
            },
            label = { Text(text = "Password") },
            placeholder = { Text(text = "Enter your password") },
            visualTransformation = VisualTransformation.None
        )

        Button(

            modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp),
            shape = RoundedCornerShape(15.dp),
            onClick = {
                registerViewModel.register()
            }
        ) {
            Text(
                text = "Sign Up",
                color = Color.White,
                modifier = Modifier.padding(7.dp)
            )
            if (registerViewModel.loading.value) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(24.dp)
                        .padding(7.dp),
                    color = Color.White
                )
            }

        }
        Text(
            modifier = Modifier
                .padding(15.dp)
                .clickable {
                    navController.navigate(Router.Login.route)
                },
            text = "Already Have an account? sign In",
            fontWeight = FontWeight.Bold, color = Color.Black,

        )
    }
    LaunchedEffect(key1 = registerViewModel.success.value) {
        if (registerViewModel.success.value) {
            navController.navigate(Router.Login.route)
        }
    }
    LaunchedEffect(key1 = registerViewModel.error.value) {
        if (registerViewModel.error.value.isNotEmpty()) {
            scope.launch {
                if (registerViewModel.error.value.isNotBlank()) {
                    Toast.makeText(context, registerViewModel.error.value, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    LaunchedEffect(key1 = authViewModel.isLoggedIn.value) {
        if(authViewModel.isLoggedIn.value){
            navController.navigate(Router.Profile.route)
        }
    }

}