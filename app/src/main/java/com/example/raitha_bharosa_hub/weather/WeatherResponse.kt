package com.example.raitha_bharosa_hub.weather

data class WeatherResponse(

    val main: Main,

    val weather: List<Weather>
)

data class Main(

    val temp: Double,

    val humidity: Int
)

data class Weather(

    val description: String
)