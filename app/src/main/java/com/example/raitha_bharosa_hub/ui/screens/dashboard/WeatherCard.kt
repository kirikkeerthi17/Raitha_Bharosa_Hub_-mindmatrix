package com.example.raitha_bharosa_hub.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.raitha_bharosa_hub.weather.RetrofitInstance
import kotlinx.coroutines.launch

@Composable
fun WeatherCard() {

    var temp by remember {
        mutableStateOf("--")
    }

    var humidity by remember {
        mutableStateOf("--")
    }

    var condition by remember {
        mutableStateOf("Loading...")
    }

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {

        scope.launch {

            try {

                val response =
                    RetrofitInstance.api.getWeather(

                        lat = 12.97,
                        lon = 77.59,

                        apiKey =
                            "2b55554baadb4970eb3cf61a96932ab4"
                    )

                temp =
                    "${response.main.temp}°C"

                humidity =
                    "${response.main.humidity}%"

                condition =
                    response.weather[0].description

            } catch (e: Exception) {

                condition = "Failed"
            }
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Column(
            modifier = Modifier
                .background(Color(0xFF1B5E20))
                .padding(20.dp)
        ) {

            Text(
                text = "🌦 Live Weather",
                color = Color.White,
                fontSize = 22.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "🌡 Temperature: $temp",
                color = Color.White
            )

            Text(
                text = "💧 Humidity: $humidity",
                color = Color.White
            )

            Text(
                text = "☁ Condition: $condition",
                color = Color.White
            )
        }
    }
}