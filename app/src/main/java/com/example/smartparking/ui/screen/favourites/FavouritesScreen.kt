package com.example.smartparking.ui.screen.favourites

import android.app.Activity
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.smartparking.App
import com.example.smartparking.R
import com.example.smartparking.ui.theme.DividerGrey

@Composable
fun FavouritesScreen(navController: NavHostController, context: Context) {

    val activity = LocalContext.current as Activity
    val application = activity.application as App
    val mainDatabase = application.mainDatabase

    val viewModel: FavouriteViewModel = viewModel(
        factory = FavouriteViewModel.FavouriteViewModelFactory(
            application,
            mainDatabase
        )
    )

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.
            padding(top = 35.dp),
            text = stringResource(id = R.string.favouritesScreen),
            color = Color.Black,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier
            .padding(top = 50.dp)
        )

        val favouriteName = listOf("Парковка 1", "Парковка 2", "Парковка 3", "Парковка 4",
            "Парковка 5", "Парковка 6", "Парковка 7", "Парковка 8", "Парковка 9", "Парковка 10")

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            itemsIndexed(
                favouriteName
            ) {index, item ->
                FavouriteItem(item)
                if(index != (favouriteName.size - 1)) {
                    Divider(modifier = Modifier
                        .padding(start = 30.dp, end = 30.dp, top = 15.dp, bottom = 15.dp),
                        thickness = 1.dp,
                        color = DividerGrey
                    )
                }
            }
        }
    }
}

@Composable
fun FavouriteItem(name: String) {
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
                painter = painterResource(id = R.drawable.favourites_icon),
                contentDescription = "",
                modifier = Modifier
                    .size(42.dp)
            )
            Column {
                Text(
                    text = name,
                    color = Color.Black,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 15.dp)
                )
                Text(
                    text = "Адрес",
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