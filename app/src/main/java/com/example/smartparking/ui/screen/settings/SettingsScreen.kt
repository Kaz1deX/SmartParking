package com.example.smartparking.ui.screen.settings

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.smartparking.R
import com.example.smartparking.ui.theme.DividerGrey

@Composable
fun SettingsScreen(navController: NavHostController, context: Context) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.
                padding(top = 35.dp),
            text = "Настройки",
            color = Color.Black,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier
            .padding(top = 50.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Поддержка",
                color = Color.Black,
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )
            IconButton(
                modifier = Modifier
                    .height(35.dp)
                    .width(35.dp),
                onClick = { /*TODO*/ }
            ) {
                Icon(painter = painterResource(id = R.drawable.buttonarrow), contentDescription = "")
            }
        }

        Divider(modifier = Modifier
            .padding(start = 30.dp, end = 30.dp, top = 15.dp, bottom = 15.dp),
            thickness = 1.dp,
            color = DividerGrey
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Уведомления",
                color = Color.Black,
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )
            IconButton(
                modifier = Modifier
                    .height(35.dp)
                    .width(35.dp),
                onClick = { /*TODO*/ }
            ) {
                Icon(painter = painterResource(id = R.drawable.buttonarrow), contentDescription = "")
            }
        }

        Divider(modifier = Modifier
            .padding(start = 30.dp, end = 30.dp, top = 15.dp, bottom = 15.dp),
            thickness = 1.dp,
            color = DividerGrey
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Информация",
                color = Color.Black,
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )
            IconButton(
                modifier = Modifier
                    .height(35.dp)
                    .width(35.dp),
                onClick = { /*TODO*/ }
            ) {
                Icon(painter = painterResource(id = R.drawable.buttonarrow), contentDescription = "")
            }
        }

        Divider(modifier = Modifier
            .padding(start = 30.dp, end = 30.dp, top = 15.dp, bottom = 15.dp),
            thickness = 1.dp,
            color = DividerGrey
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "О приложении",
                color = Color.Black,
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )
            IconButton(
                modifier = Modifier
                    .height(35.dp)
                    .width(35.dp),
                onClick = { /*TODO*/ }
            ) {
                Icon(painter = painterResource(id = R.drawable.buttonarrow), contentDescription = "")
            }
        }
    }
}