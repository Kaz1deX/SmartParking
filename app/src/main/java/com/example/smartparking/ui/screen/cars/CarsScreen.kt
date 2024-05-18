package com.example.smartparking.ui.screen.cars

import android.content.Context
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.smartparking.R
import com.example.smartparking.navigation.Screen
import com.example.smartparking.ui.screen.favourites.FavouriteItem
import com.example.smartparking.ui.screen.login.LoginScreen
import com.example.smartparking.ui.theme.DividerGrey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarsScreen(navController: NavHostController, context: Context) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),

        topBar = {
            TopAppBar(
                title = {
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "Добавленные машины", fontWeight = FontWeight.Medium,
                        color = Color.Black,

                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Go Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.AddCarScreen.route)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add"
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
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(values)
//        )


        Column (
//            modifier = Modifier.fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally
            modifier = Modifier
                .padding(values)
                .fillMaxSize()

        ) {
            val car = listOf(
                listOf("Мерседес 1", "ооо12оо"),
                listOf("БМВ 2", "авп65ии"),
                listOf("БМВ 2", "авп65ии"),
                listOf("БМВ 2", "авп65ии"),
                listOf("БМВ 2", "авп65ии"),
                listOf("БМВ 2", "авп65ии"),
                listOf("БМВ 2", "авп65ии"),
                listOf("БМВ 2", "авп65ии"),
                listOf("БМВ 2", "авп65ии"),
                listOf("БМВ 2", "авп65ии"),
                listOf("БМВ 2", "авп65ии"),
                listOf("БМВ 2", "авп65ии"),
                listOf("БМВ 2", "авп65ии"),
                listOf("БМВ 2", "авп65ии"),
            )

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 15.dp)
            ) {
                itemsIndexed(
                    car
                ) {index, item ->
                    CarItem(item)
                    if(index != (car.size - 1)) {
                        Divider(modifier = Modifier
                            .padding(start = 30.dp, end = 30.dp, top = 15.dp, bottom = 15.dp),
                            thickness = 1.dp,
                            color = DividerGrey
                        )
                    }
                }
            }
        }




//        ) {
//            Column(
//                modifier = Modifier
//                    .padding(horizontal = 16.dp)
//                    .verticalScroll(scrollState)
//            ) {
//                TextField(
//                    hint = "Предмет",
//                    text = textSubject,
//                    keyboardOptions = KeyboardOptions(),
//                    keyboardActions = KeyboardActions(),
//                    horizontalPadding = 0.dp
//                )
//
//                Spacer(modifier = Modifier.padding(8.dp))
//
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Checkbox(
//                        checked = isCheckBoxOpenDate,
//                        onCheckedChange = { newValue -> isCheckBoxOpenDate = newValue },
//                        colors = CheckboxDefaults.colors(
//                            checkedColor = Green,
//                            uncheckedColor = Gray
//                        )
//                    )
//                    Text(text = "Открытие по времени", color = Color.White, fontSize = 16.sp)
//                }

//            }
//        }
    }
}

@Composable
fun CarItem(car: List<String>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp)
            .clip(RoundedCornerShape(10.dp))
            .height(50.dp)
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
            Icon(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .size(42.dp)
            )
            Column {
                Text(
                    text = "Машина: " + car[0],
                    color = Color.Black,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 15.dp)
                )
                Text(
                    text = "Номер машины: " + car[1],
                    color = Color.Gray,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 15.dp, top = 5.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun CarsScreenPreview() {
    CarsScreen(navController = rememberNavController(), context = LocalContext.current)
}