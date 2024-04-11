package com.example.smartparking.ui.screen.settings

import android.content.Context
import android.provider.Settings.Global.getString
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
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
            text = stringResource(id = R.string.settingsScreen),
            color = Color.Black,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier
            .padding(top = 50.dp)
        )

        val settingsName = stringArrayResource(id = R.array.settings)

        for(i in settingsName.indices) {
            SettingsItem(settingsName[i])
            if(i != (settingsName.size - 1)) {
                Divider(modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp, top = 15.dp, bottom = 15.dp),
                    thickness = 1.dp,
                    color = DividerGrey
                )
            }
        }
    }
}


@Composable
fun SettingsItem(name: String) {
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
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 15.dp, end = 15.dp),
        ) {
            Text(
                text = name,
                color = Color.Black,
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )
            Icon(painter = painterResource(id = R.drawable.buttonarrow), contentDescription = "")
        }
    }
}