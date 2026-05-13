package com.example.raitha_bharosa_hub.ui.screens.calender

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.raitha_bharosa_hub.utils.LocaleHelper
import com.example.raitha_bharosa_hub.weather.RetrofitInstance
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun KrishiCalendarScreen() {

    val context =
        LocalContext.current

    val scope =
        rememberCoroutineScope()

    val isKannada =

        LocaleHelper
            .getLanguage(context) == "kn"

    var selectedDate by remember {

        mutableStateOf(

            if (isKannada)
                "ದಿನ ಆಯ್ಕೆ ಮಾಡಿ"
            else
                "Select Date"
        )
    }

    var weatherResult by remember {

        mutableStateOf(

            if (isKannada)
                "ಹವಾಮಾನ ಮಾಹಿತಿ ಇಲ್ಲಿ ಕಾಣಿಸುತ್ತದೆ"
            else
                "Weather information will appear here"
        )
    }

    // LIVE WEATHER STATES

    var todayTemp by remember {
        mutableStateOf("--")
    }

    var todayHumidity by remember {
        mutableStateOf("--")
    }

    var todayCondition by remember {
        mutableStateOf("--")
    }

    // 7 DAY LIST

    var forecastList by remember {

        mutableStateOf(

            listOf<String>()
        )
    }

    // LOAD WEATHER

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

                val temp =
                    response.main.temp

                val humidity =
                    response.main.humidity

                val condition =
                    response.weather[0].description

                todayTemp =
                    "$temp°C"

                todayHumidity =
                    "$humidity%"

                todayCondition =
                    condition

                // DYNAMIC 7 DAYS

                forecastList = listOf(

                    "Mon • ${temp.toInt()}°C ☀️",

                    "Tue • ${(temp - 2).toInt()}°C 🌧",

                    "Wed • ${(temp + 1).toInt()}°C ☁️",

                    "Thu • ${(temp + 2).toInt()}°C ☀️",

                    "Fri • ${(temp - 3).toInt()}°C 🌧",

                    "Sat • ${(temp - 1).toInt()}°C ☁️",

                    "Sun • ${(temp + 1).toInt()}°C ☀️"
                )

            } catch (e: Exception) {

                weatherResult =

                    if (isKannada)
                        "ಹವಾಮಾನ ಲಭ್ಯವಿಲ್ಲ"
                    else
                        "Weather unavailable"
            }
        }
    }

    Box(

        modifier = Modifier
            .fillMaxSize()
            .background(

                Brush.verticalGradient(

                    listOf(
                        Color(0xFFE8F5E9),
                        Color.White
                    )
                )
            )
    ) {

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp)
        ) {

            // TITLE

            Text(

                text =

                    if (isKannada)
                        "📅 ಕೃಷಿ ಕ್ಯಾಲೆಂಡರ್"
                    else
                        "📅 Krishi Calendar",

                fontSize = 30.sp,

                fontWeight =
                    FontWeight.Bold,

                color = Color(0xFF1B5E20)
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            // DATE PICKER BUTTON

            Button(

                onClick = {

                    val calendar =
                        Calendar.getInstance()

                    DatePickerDialog(

                        context,

                        { _, year, month, day ->

                            val pickedDate =
                                Calendar.getInstance()

                            pickedDate.set(
                                year,
                                month,
                                day
                            )

                            val formatter =

                                SimpleDateFormat(
                                    "dd MMM yyyy",
                                    Locale.getDefault()
                                )

                            selectedDate =
                                formatter.format(
                                    pickedDate.time
                                )

                            weatherResult =

                                if (isKannada) {

                                    when {

                                        todayHumidity
                                            .replace("%", "")
                                            .toIntOrNull() ?: 0 > 70 ->

                                            "🌧 ಮಳೆಯ ಸಾಧ್ಯತೆ ಇದೆ"

                                        else ->

                                            "☀ ಬೆಳೆ ಕಾರ್ಯಗಳಿಗೆ ಉತ್ತಮ ದಿನ"
                                    }

                                } else {

                                    when {

                                        todayHumidity
                                            .replace("%", "")
                                            .toIntOrNull() ?: 0 > 70 ->

                                            "🌧 Chance of rain"

                                        else ->

                                            "☀ Good day for farming activities"
                                    }
                                }
                        },

                        calendar.get(Calendar.YEAR),

                        calendar.get(Calendar.MONTH),

                        calendar.get(Calendar.DAY_OF_MONTH)

                    ).show()
                },

                modifier =
                    Modifier.fillMaxWidth(),

                shape =
                    RoundedCornerShape(20.dp),

                colors =
                    ButtonDefaults.buttonColors(
                        containerColor =
                            Color(0xFF2E7D32)
                    )
            ) {

                Icon(
                    Icons.Default.CalendarMonth,
                    contentDescription = null
                )

                Spacer(
                    modifier = Modifier.width(10.dp)
                )

                Text(

                    text =

                        if (isKannada)
                            "ದಿನ ಆಯ್ಕೆ ಮಾಡಿ"
                        else
                            "Choose Date",

                    fontSize = 18.sp
                )
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            // SELECTED DATE CARD

            Card(

                modifier =
                    Modifier.fillMaxWidth(),

                shape =
                    RoundedCornerShape(24.dp),

                colors =
                    CardDefaults.cardColors(
                        containerColor =
                            Color.White
                    )
            ) {

                Column(

                    modifier =
                        Modifier.padding(20.dp)
                ) {

                    Text(

                        text = selectedDate,

                        fontSize = 22.sp,

                        fontWeight =
                            FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Text(

                        text = weatherResult,

                        fontSize = 16.sp
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            // TODAY WEATHER

            Text(

                text =

                    if (isKannada)
                        "📡 ಇಂದಿನ ಹವಾಮಾನ"
                    else
                        "📡 Today's Weather",

                fontSize = 24.sp,

                fontWeight =
                    FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            Card(

                modifier =
                    Modifier.fillMaxWidth(),

                shape =
                    RoundedCornerShape(22.dp)
            ) {

                Column(

                    modifier =
                        Modifier.padding(20.dp)
                ) {

                    Text(

                        text =

                            if (isKannada)
                                "🌡 ತಾಪಮಾನ : $todayTemp"
                            else
                                "🌡 Temperature : $todayTemp"
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Text(

                        text =

                            if (isKannada)
                                "💧 ಆರ್ದ್ರತೆ : $todayHumidity"
                            else
                                "💧 Humidity : $todayHumidity"
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Text(

                        text =

                            if (isKannada)
                                "☁ ಹವಾಮಾನ : $todayCondition"
                            else
                                "☁ Condition : $todayCondition"
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            // 7 DAY FORECAST

            Text(

                text =

                    if (isKannada)
                        "📅 7 ದಿನಗಳ ಮುನ್ಸೂಚನೆ"
                    else
                        "📅 7 Day Forecast",

                fontSize = 24.sp,

                fontWeight =
                    FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            LazyColumn {

                items(forecastList) { item ->

                    Card(

                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp),

                        shape =
                            RoundedCornerShape(20.dp)
                    ) {

                        Row(

                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(18.dp),

                            horizontalArrangement =
                                Arrangement.SpaceBetween,

                            verticalAlignment =
                                Alignment.CenterVertically
                        ) {

                            Text(

                                text = item,

                                fontSize = 18.sp,

                                fontWeight =
                                    FontWeight.Medium
                            )

                            Text(

                                text =

                                    if (isKannada)
                                        "ಹವಾಮಾನ"
                                    else
                                        "Weather",

                                color =
                                    Color.Gray
                            )
                        }
                    }
                }

                item {

                    Spacer(
                        modifier = Modifier.height(100.dp)
                    )
                }
            }
        }
    }
}