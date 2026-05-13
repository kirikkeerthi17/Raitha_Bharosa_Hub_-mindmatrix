package com.example.raitha_bharosa_hub.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.raitha_bharosa_hub.weather.RetrofitInstance
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    var temperature by mutableStateOf("--")
        private set

    var humidity by mutableStateOf("--")
        private set

    var condition by mutableStateOf("--")
        private set

    var rainChance by mutableStateOf("--")
        private set

    var sowingScore by mutableFloatStateOf(0.65f)
        private set

    init {

        fetchWeather()
    }

    private fun fetchWeather() {

        viewModelScope.launch {

            try {

                val response =
                    RetrofitInstance.api.getWeather(

                        lat = 12.97,
                        lon = 77.59,

                        apiKey =
                            "2b55554baadb4970eb3cf61a96932ab4"
                    )

                val temp =
                    response.main.temp

                val hum =
                    response.main.humidity

                val weatherCondition =
                    response.weather[0].description

                temperature = "$temp°C"

                humidity = "$hum%"

                condition = weatherCondition

                rainChance =

                    when {

                        hum > 80 -> "75%"

                        hum > 60 -> "45%"

                        else -> "20%"
                    }

                sowingScore = when {

                    temp in 20.0..28.0 &&
                            hum > 65 -> 0.91f

                    temp in 18.0..32.0 &&
                            hum > 50 -> 0.76f

                    else -> 0.49f
                }

            } catch (e: Exception) {

                condition = "Unavailable"
            }
        }
    }
}