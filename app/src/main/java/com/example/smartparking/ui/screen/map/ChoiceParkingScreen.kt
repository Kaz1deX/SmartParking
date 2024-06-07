package com.example.smartparking.ui.screen.map

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.graphics.Color
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
import com.example.smartparking.ui.screen.profile.ProfileViewModel
import com.example.smartparking.ui.theme.Black18
import com.example.smartparking.ui.theme.Blue
import com.example.smartparking.ui.theme.DividerGrey
import com.example.smartparking.ui.theme.Whiteff
import com.example.smartparking.util.Constants
import com.example.smartparking.util.formatDate
import com.maxkeppeker.sheets.core.models.base.IconSource
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import com.maxkeppeler.sheets.list.ListDialog
import com.maxkeppeler.sheets.list.models.ListOption
import com.maxkeppeler.sheets.list.models.ListSelection

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

    val mapViewModel: MapViewModel = viewModel(
        factory = MapViewModel.MapViewModelFactory(
            application,
            repository
        )
    )

    val profileViewModel: ProfileViewModel = viewModel(
        factory = ProfileViewModel.ProfileViewModelFactory(
            application,
            repository
        )
    )

    val parking = mapViewModel.parkingOne.collectAsState()
    val availableSlots = mapViewModel.availableSlots.collectAsState()
    val cars = profileViewModel.cars.collectAsState()
    val amount = mapViewModel.amount.collectAsState()

    val stateParkingInfo = rememberSaveable { mutableStateOf(false) }
    val stateAvailablePlaces = rememberSaveable { mutableStateOf(false) }
    val showExitDialog = rememberSaveable { mutableStateOf(false) }
    val showMinimalDialog = rememberSaveable { mutableStateOf(false) }

    val textDateSelected = rememberSaveable { mutableStateOf("Выбрать дату бронирования") }
    val textCarSelected = rememberSaveable { mutableStateOf("Выбрать автомобиль") }

    val calendarStateOpen = rememberSheetState()
    val listCarStateOpen = rememberSheetState()

    val imageLoader = ImageLoader.Builder(context).components { add(SvgDecoder.Factory()) }.build()

    LaunchedEffect(key1 = Unit) {
        profileViewModel.getCars(onResult = {})
        mapViewModel.getParkingById(
            parkingId,
            onResult = { stateParkingInfo.value = !stateParkingInfo.value })
    }

    if (textDateSelected.value != "Выбрать дату бронирования") {
        mapViewModel.getAvailableSlots(
            parkingId,
            textDateSelected.value,
            onResult = { stateAvailablePlaces.value = true })
    }

    CalendarDialog(
        state = calendarStateOpen,
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true,
            style = CalendarStyle.MONTH
        ),
        selection = CalendarSelection.Date { date ->
            textDateSelected.value = formatDate(date.toString())
        }
    )

    val options = cars.value.map {
        ListOption(
            IconSource(R.drawable.profile_icon),
            titleText = "${it.model} ${it.number}"
        )
    }

    ListDialog(
        state = listCarStateOpen,
        selection = ListSelection.Single(
            showRadioButtons = true,
            options = options
        ) { index, option ->
            textCarSelected.value = option.titleText
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
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { values ->
        if (stateParkingInfo.value) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(values)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
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
                            listCarStateOpen.show()
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
                            text = textCarSelected.value,
                            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight(500))
                        )
                    }
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
                            text = textDateSelected.value,
                            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight(500))
                        )
                    }
                }

                if (stateAvailablePlaces.value) {
                    FlowRow(
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 15.dp)
                    ) {
                        availableSlots.value.forEach {
                            ActionChips(item = it, mapViewModel)
                        }
                    }
                }

                if (amount.value > 0) {
                    Text(
                        modifier = Modifier.padding(bottom = 15.dp),
                        text = "Итоговая сумма заказа: ${parking.value!!.costPerHour * amount.value}₽"
                    )
                }

                Box(
                    modifier = Modifier.padding(start = 22.dp, end = 22.dp, bottom = 24.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Button(
                        onClick = {
                            showExitDialog.value = !showExitDialog.value
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
        if (showExitDialog.value) {
            AlertDialog(
                onDismissRequest = { showExitDialog.value = false },
                title = { Text(text = "Подтверждение бронирования", textAlign = TextAlign.Center) },
                text = { Text(textAlign = TextAlign.Center, text = "Вы подтверждаете бронирование парковки по адресу: ${parking.value!!.address}?\n\nИтоговая сумма заказа: ${parking.value!!.costPerHour * amount.value}₽") },
                confirmButton = {
                    Button(
                        onClick = {
                            mapViewModel.createBookings(
                                parkingId,
                                textCarSelected.value,
                                parking.value!!.name,
                                textDateSelected.value
                            )
                            showExitDialog.value = !showExitDialog.value
                            showMinimalDialog.value = !showMinimalDialog.value
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
        if (showMinimalDialog.value) {
            MinimalDialog(onDismissRequest = {}, navController = navController)
        }
    }
}

@Composable
fun MinimalDialog(onDismissRequest: () -> Unit, navController: NavHostController) {
    val showMinimalDialog = rememberSaveable { mutableStateOf(true) }
    if (showMinimalDialog.value) {
        AlertDialog(
            onDismissRequest = onDismissRequest,
            icon = {
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.done),
                        contentDescription = "Confirmation Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                    )
                }
            },
            text = {
                Text(textAlign = TextAlign.Center, text = "Парковка успешно забронирована! Проверьте Ваши заказы.")
            },
            confirmButton = {
                Button(
                    onClick = {
                        navController.navigate(Screen.MapScreen.route)
                        showMinimalDialog.value = !showMinimalDialog.value
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Black18
                    )
                ) {
                    Text("Понятно")
                }
            },
            containerColor = Whiteff,
            iconContentColor = Black18,
            textContentColor = Black18,
            titleContentColor = Black18,
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionChips(item: Pair<String, String>, viewModel: MapViewModel) {
    var isSelectedChip by remember { mutableStateOf(false) }
    val bookingIntervals = viewModel.bookingIntervals.collectAsState()
    val parking = viewModel.parkingOne.collectAsState()
    FilterChip(
        selected = isSelectedChip,
        modifier = Modifier
            .height(60.dp)
            .padding(5.dp),
        onClick = {
            isSelectedChip = !isSelectedChip
            viewModel.onIntervalSelected(item, isSelectedChip, parking.value!!.costPerHour)
            if (isSelectedChip) viewModel.incrementAmount() else viewModel.decrementAmount()
            Log.i("CheckInOutPairs: ", bookingIntervals.value.toString())
        },
        label = {
            Text("${item.first}-${item.second}")
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