package com.example.smartparking.ui.screen.profile

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.smartparking.App
import com.example.smartparking.R
import com.example.smartparking.navigation.Screen
import com.example.smartparking.ui.theme.Black18
import com.example.smartparking.ui.theme.DividerGrey
import com.example.smartparking.ui.theme.Whiteff

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(navController: NavHostController, context: Context) {
    val activity = LocalContext.current as Activity
    val application = activity.application as App
    val repository = application.repository

    val showExitDialog = rememberSaveable { mutableStateOf(false) }
    val selectedBookingId = rememberSaveable { mutableStateOf("") }

    val viewModel: ProfileViewModel = viewModel(
        factory = ProfileViewModel.ProfileViewModelFactory(
            application,
            repository
        )
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllBooking()
    }

    val booking = viewModel.booking.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        text = "Заказы",
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Screen.ProfileScreen.route) }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Go Back"
                        )
                    }
                },
                actions = {
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    navigationIconContentColor = Color.Black,
                    actionIconContentColor = Color.Black,
                    containerColor = Color.Transparent
                )
            )
        }
    ) { values ->
        Column(
            modifier = Modifier
                .padding(values)
                .fillMaxSize()
        ) {
            LazyColumn {
                itemsIndexed(booking.value) { index, item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 15.dp, end = 15.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable {

                            }
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 15.dp, end = 15.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = "",
                                modifier = Modifier.size(42.dp)
                            )
                            Column {
                                Text(
                                    text = item.parkingName,
                                    color = Color.Black,
                                    fontSize = 15.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .padding(start = 15.dp)
                                )
                                Text(
                                    text = "Время заезда: " + item.checkIn,
                                    color = Color.Gray,
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .padding(start = 15.dp, top = 5.dp)
                                )
                                Text(
                                    text = "Время выезда: " + item.exit,
                                    color = Color.Gray,
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .padding(start = 15.dp, top = 5.dp)
                                )
                                Text(
                                    text = "Сумма заказа: " + item.amount.toString(),
                                    color = Color.Gray,
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .padding(start = 15.dp, top = 5.dp)
                                )
                            }
                        }
                        IconButton(
                            onClick = {
                                showExitDialog.value = !showExitDialog.value
                                selectedBookingId.value = item.id
                            },
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                            )
                        }
                    }
                    if (index != (booking.value.size - 1)) {
                        Divider(
                            modifier = Modifier
                                .padding(start = 30.dp, end = 30.dp, top = 15.dp, bottom = 15.dp),
                            thickness = 1.dp,
                            color = DividerGrey
                        )
                    }
                }
            }
        }

        if (showExitDialog.value) {
            AlertDialog(
                onDismissRequest = { showExitDialog.value = false },
                title = { Text("Удаление автомобиля") },
                text = { Text(text = "Вы уверены, что хотите удалить данные об автомобиле?") },
                confirmButton = {
                    Button(
                        onClick = {
                            Log.i("BOOKINGID: ", selectedBookingId.value.toString())
                            viewModel.deleteBooking(selectedBookingId.value)
                            showExitDialog.value = !showExitDialog.value
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Black18
                        )
                    ) {
                        Text("Да")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            showExitDialog.value = false
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Black18,
                        )
                    ) {
                        Text("Нет")
                    }
                },
                containerColor = Whiteff,
                iconContentColor = Black18,
                textContentColor = Black18,
                titleContentColor = Black18,
            )
        }
    }
}