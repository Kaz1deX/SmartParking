package com.example.smartparking.ui.screen.map

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.smartparking.App
import com.example.smartparking.R
import com.example.smartparking.navigation.Screen
import com.example.smartparking.ui.theme.Blue
import com.example.smartparking.ui.theme.DividerGrey
import com.example.smartparking.util.Constants
import com.example.smartparking.util.formatDate
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ChoiceParkingScreen(navController: NavHostController, context: Context) {
    val parkingId = remember {
        val route =
            navController.currentBackStackEntry?.arguments?.getString(Constants.KEY_PARKING_ID)
        route?.substringAfterLast("/") ?: ""
    }


    val activity = LocalContext.current as Activity
    val application = activity.application as App
    val repository = application.repository

    val viewModel: MapViewModel = viewModel(
        factory = MapViewModel.MapViewModelFactory(
            application,
            repository
        )
    )

    val parking = viewModel.parkingOne.collectAsState()
    val screenState = rememberSaveable { mutableStateOf(false) }
    val textOpenDate = rememberSaveable { mutableStateOf("Выбрать дату бронирования") }
    val calendarStateOpen = rememberSheetState()
    val imageLoader = ImageLoader.Builder(context).components { add(SvgDecoder.Factory()) }.build()

    LaunchedEffect(key1 = Unit) {
        viewModel.getParking()
        viewModel.getParkingById(parkingId, onResult = { screenState.value = !screenState.value })
    }

    val times = listOf(
        "09:00-10:00",
        "10:00-11:00",
        "11:00-12:00",
        "12:00-13:00",
        "13:00-14:00",
        "14:00-15:00",
        "15:00-16:00",
        "16:00-17:00",
        "17:00-18:00",
        "18:00-19:00",
        "20:00-21:00"
    )

    CalendarDialog(
        state = calendarStateOpen,
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true,
            style = CalendarStyle.MONTH
        ),
        selection = CalendarSelection.Date { date ->
            textOpenDate.value = formatDate(date.toString())
        }
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),

        topBar = {
            TopAppBar(
                title = {
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        text = "Информация о парковке", fontWeight = FontWeight.Medium,
                        color = Color.Black,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Screen.MapScreen.route) }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Go Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    navigationIconContentColor = Color.Black,
                    actionIconContentColor = Color.Black,
                    containerColor = Color.Transparent
                )
            )
        }
    ) { values ->
        if (screenState.value) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(values)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(parking.value!!.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = "profile_image",
                    modifier = Modifier
                        .fillMaxWidth(),
                    imageLoader = imageLoader
                )
                Spacer(
                    modifier = Modifier
                        .padding(top = 15.dp)
                )
                Text(
                    modifier = Modifier.padding(start = 15.dp, end = 15.dp),
                    text = parking.value!!.name,
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(
                    modifier = Modifier
                        .padding(top = 15.dp)
                )

                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                        text = "Адрес",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                        text = parking.value!!.address,
                        color = Color.Gray,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start
                    )
                    Divider(
                        modifier = Modifier
                            .padding(start = 30.dp, end = 30.dp, top = 25.dp, bottom = 25.dp),
                        thickness = 1.dp,
                        color = DividerGrey
                    )

                    Text(
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                        text = "Описание",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                        text = parking.value!!.description,
                        color = Color.Gray,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start
                    )
                    Divider(
                        modifier = Modifier
                            .padding(start = 30.dp, end = 30.dp, top = 25.dp, bottom = 25.dp),
                        thickness = 1.dp,
                        color = DividerGrey
                    )

                    Text(
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                        text = "Количество мест",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                        text = parking.value!!.totalPlaces.toString(),
                        color = Color.Gray,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start
                    )
                    Divider(
                        modifier = Modifier
                            .padding(start = 30.dp, end = 30.dp, top = 25.dp, bottom = 25.dp),
                        thickness = 1.dp,
                        color = DividerGrey
                    )

                    Text(
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                        text = "Цена в час",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                        text = parking.value!!.costPerHour.toString(),
                        color = Color.Gray,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start
                    )
                    Divider(
                        modifier = Modifier
                            .padding(start = 30.dp, end = 30.dp, top = 25.dp, bottom = 25.dp),
                        thickness = 1.dp,
                        color = DividerGrey
                    )

                    Text(
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                        text = "Наличие зарядной станции",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                        text = if (parking.value!!.chargingStation) "Да" else "Нет",
                        color = Color.Gray,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start
                    )
                    Divider(
                        modifier = Modifier
                            .padding(start = 30.dp, end = 30.dp, top = 25.dp, bottom = 10.dp),
                        thickness = 1.dp,
                        color = DividerGrey
                    )
                }

                Box(
                    modifier = Modifier.padding(start = 22.dp, end = 22.dp, bottom = 24.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Button(
                        onClick = {
                            calendarStateOpen.show()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    ) {
                        Text(
                            text = textOpenDate.value,
                            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight(500))
                        )
                    }
                }

                FlowRow(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 15.dp)
                ) {
                    times.forEach {
                        ActionChips(item = it)
                    }
                }

                Box(
                    modifier = Modifier.padding(start = 22.dp, end = 22.dp, bottom = 24.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Button(
                        onClick = {

                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    ) {
                        Text(
                            text = "Забронировать",
                            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight(500))
                        )
                    }
                }
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                androidx.compose.material3.CircularProgressIndicator(
                    color = Blue
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ActionChips(item: String) {
//    Chip(
//        modifier = Modifier
//            .padding(8.dp),
//        onClick = {
//
//        }
//    ) {
//        Text(text = item)
//    }
    var isSelectedChip by remember { mutableStateOf(false) }
    FilterChip(
        selected = isSelectedChip,
        modifier = Modifier
            .height(60.dp)
            .padding(5.dp),
        onClick = {
            isSelectedChip = !isSelectedChip
        },
        label = {
            Text(item)
        },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = Blue,
            selectedLabelColor = Color.White
        )
    )
}

@Preview
@Composable
fun ChoiceParkingScreenPreview() {
    ChoiceParkingScreen(navController = rememberNavController(), context = LocalContext.current)
}