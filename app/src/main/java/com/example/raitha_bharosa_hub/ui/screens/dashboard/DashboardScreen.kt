package com.example.raitha_bharosa_hub.ui.screens.dashboard
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.raitha_bharosa_hub.viewmodel.DashboardViewModel
import android.app.Activity
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.raitha_bharosa_hub.utils.LocaleHelper

import kotlinx.coroutines.delay


@Composable
fun DashboardScreen(

    userName: String,

    cropName: String,

    onSoilClick: () -> Unit,

    onKrishiClick: () -> Unit,

    onSettingsClick: () -> Unit,

    onChatClick: () -> Unit
) {

    val context = LocalContext.current


    val viewModel: DashboardViewModel =
        viewModel()

    var isKannada by remember {

        mutableStateOf(
            LocaleHelper.getLanguage(context) == "kn"
        )
    }

    // LANGUAGE TEXTS

    var latestUserName by remember {

        mutableStateOf(userName)
    }

    LaunchedEffect(userName) {

        latestUserName = userName
    }

    val helloText =

        if (isKannada)
            "👋 ನಮಸ್ಕಾರ $latestUserName"
        else
            "👋 Hello $latestUserName"

    val weatherTitle =
        if (isKannada)
            "📡 ನೇರ ಹವಾಮಾನ"
        else
            "📡 Live Weather"

    val recommendationTitle =
        if (isKannada)
            "🌱 ಇಂದಿನ ಸಲಹೆ"
        else
            "🌱 Recommendation"

    val sowingText =
        if (isKannada)
            "ಬಿತ್ತನೆ ಸೂಚ್ಯಂಕ"
        else
            "Sowing Index"

    val soilText =
        if (isKannada)
            "ಮಣ್ಣಿನ ವಿಶ್ಲೇಷಣೆ"
        else
            "Soil Analytics"

    val krishiText =
        if (isKannada)
            "ಕೃಷಿ ಕ್ಯಾಲೆಂಡರ್"
        else
            "Krishi Calendar"

    val settingsText =
        if (isKannada)
            "ಸೆಟ್ಟಿಂಗ್ಸ್"
        else
            "Settings"

    val dashboardText =
        if (isKannada)
            "ಡ್ಯಾಶ್‌ಬೋರ್ಡ್"
        else
            "Dashboard"

    // weather states


    val temperature =
        viewModel.temperature

    val humidity =
        viewModel.humidity

    val condition =
        viewModel.condition

    val rainChance =
        viewModel.rainChance

    val sowingScore =
        viewModel.sowingScore


    // RECOMMENDATIONS

    val englishRecommendations = listOf(

        "Use drip irrigation today",
        "Best day for fertilizer spraying",
        "Avoid over watering crops",
        "Good humidity for cotton",
        "Rain expected tomorrow",
        "Protect crops from pests",
        "Perfect weather for sowing",
        "Monitor soil moisture today",
        "Ideal climate for crop growth",
        "Use balanced NPK fertilizer",
        "Strong winds expected",
        "Harvest conditions are good",
        "Check leaf diseases",
        "Maintain proper irrigation",
        "Mulching recommended today",
        "Good day for spraying pesticide",
        "Avoid field flooding",
        "Keep crops hydrated",
        "Organic manure recommended",
        "High humidity detected"
    )

    val kannadaRecommendations = listOf(

        "ಇಂದು ಡ್ರಿಪ್ ನೀರಾವರಿ ಬಳಸಿ",
        "ರಸಗೊಬ್ಬರ ಸಿಂಪಡಿಸಲು ಉತ್ತಮ ದಿನ",
        "ಹೆಚ್ಚು ನೀರು ಹಾಕಬೇಡಿ",
        "ಹತ್ತಿಗೆ ಉತ್ತಮ ಆರ್ದ್ರತೆ",
        "ನಾಳೆ ಮಳೆಯ ಸಾಧ್ಯತೆ ಇದೆ",
        "ಬೆಳೆಗಳನ್ನು ಕೀಟಗಳಿಂದ ರಕ್ಷಿಸಿ",
        "ಬಿತ್ತನೆಗೆ ಉತ್ತಮ ಹವಾಮಾನ",
        "ಮಣ್ಣಿನ ತೇವಾಂಶ ಪರಿಶೀಲಿಸಿ",
        "ಬೆಳೆ ಬೆಳವಣಿಗೆಗೆ ಉತ್ತಮ ಹವಾಮಾನ",
        "NPK ರಸಗೊಬ್ಬರ ಬಳಸಿ",
        "ಜೋರಾದ ಗಾಳಿ ನಿರೀಕ್ಷೆ",
        "ಕೊಯ್ಲಿಗೆ ಉತ್ತಮ ಪರಿಸ್ಥಿತಿ",
        "ಎಲೆ ರೋಗಗಳನ್ನು ಪರಿಶೀಲಿಸಿ",
        "ಸರಿಯಾದ ನೀರಾವರಿ ಕಾಪಾಡಿ",
        "ಮಲ್ಚಿಂಗ್ ಮಾಡಲು ಉತ್ತಮ ದಿನ",
        "ಕೀಟನಾಶಕ ಸಿಂಪಡಿಸಿ",
        "ಹೊಲದಲ್ಲಿ ನೀರು ನಿಲ್ಲದಂತೆ ನೋಡಿ",
        "ಬೆಳೆಗಳಿಗೆ ತೇವಾಂಶ ಕಾಪಾಡಿ",
        "ಸಾವಯವ ಗೊಬ್ಬರ ಬಳಸಿ",
        "ಹೆಚ್ಚು ಆರ್ದ್ರತೆ ಕಂಡುಬಂದಿದೆ"
    )

    var recommendationIndex by remember {

        mutableIntStateOf(0)
    }

    val recommendations =

        if (isKannada)
            kannadaRecommendations
        else
            englishRecommendations

    val recommendation =
        recommendations[recommendationIndex]

    // AUTO CHANGE EVERY 2 MINUTES

    LaunchedEffect(Unit) {

        while (true) {

            delay(120000)

            recommendationIndex =

                (recommendationIndex + 1) %
                        recommendations.size
        }
    }




    val progress by animateFloatAsState(

        targetValue = sowingScore,

        animationSpec = tween(2000),

        label = ""
    )

    Scaffold(

        floatingActionButton = {

            FloatingActionButton(

                onClick = {

                    onChatClick()
                },

                containerColor =
                    Color(0xFF2E7D32)
            ){

                Icon(

                    Icons.Default.Chat,

                    contentDescription = null,

                    tint = Color.White
                )
            }
        },

        bottomBar = {

            NavigationBar {

                NavigationBarItem(

                    selected = true,

                    onClick = { },

                    icon = {

                        Icon(
                            Icons.Default.Home,
                            null
                        )
                    },

                    label = {

                        Text(dashboardText)
                    }
                )

                NavigationBarItem(

                    selected = false,

                    onClick = {

                        onSoilClick()
                    },

                    icon = {

                        Icon(
                            Icons.Default.Science,
                            null
                        )
                    },

                    label = {

                        Text(soilText)
                    }
                )

                NavigationBarItem(

                    selected = false,

                    onClick = {

                        onKrishiClick()
                    },

                    icon = {

                        Icon(
                            Icons.Default.CalendarMonth,
                            null
                        )
                    },

                    label = {

                        Text(krishiText)
                    }
                )

                NavigationBarItem(

                    selected = false,

                    onClick = {

                        onSettingsClick()
                    },

                    icon = {

                        Icon(
                            Icons.Default.Settings,
                            null
                        )
                    },

                    label = {

                        Text(settingsText)
                    }
                )
            }
        }

    ) { padding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF4F7F9))
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(padding)
                .padding(16.dp)
        ) {

            // TOP CARD

            Card(

                shape =
                    RoundedCornerShape(30.dp)
            ) {

                Box(

                    modifier =
                        Modifier
                            .background(

                                Brush.horizontalGradient(

                                    listOf(
                                        Color(0xFF1B5E20),
                                        Color(0xFF66BB6A)
                                    )
                                )
                            )
                            .padding(24.dp)
                ) {

                    Row(

                        modifier =
                            Modifier.fillMaxWidth(),

                        horizontalArrangement =
                            Arrangement.SpaceBetween,

                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Column {

                            Text(

                                text = helloText,

                                color = Color.White,

                                fontSize = 24.sp,

                                fontWeight =
                                    FontWeight.Bold
                            )

                            Spacer(
                                modifier =
                                    Modifier.height(8.dp)
                            )

                            Text(

                                text =
                                    "$cropName • Bengaluru",

                                color = Color.White
                            )

                            Spacer(
                                modifier =
                                    Modifier.height(8.dp)
                            )

                            Text(

                                text =
                                    "$temperature • $humidity",

                                color = Color.White
                            )
                        }

                        IconButton(

                            onClick = {

                                isKannada =
                                    !isKannada

                                LocaleHelper.setLocale(

                                    context as Activity,

                                    if (isKannada)
                                        "kn"
                                    else
                                        "en"
                                )
                            }
                        ) {

                            Icon(

                                Icons.Default.Language,

                                contentDescription = null,

                                tint = Color.White
                            )
                        }
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            // SOWING INDEX

            Card(

                shape =
                    RoundedCornerShape(28.dp)
            ) {

                Column(

                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(24.dp),

                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {

                    Box(

                        contentAlignment =
                            Alignment.Center
                    ) {

                        CircularProgressIndicator(

                            progress = { progress },

                            modifier =
                                Modifier.size(140.dp),

                            color =
                                Color(0xFF43A047),

                            strokeWidth = 12.dp,

                            strokeCap =
                                StrokeCap.Round
                        )

                        Text(

                            text =
                                "${(sowingScore * 100).toInt()}%",

                            fontSize = 30.sp,

                            fontWeight =
                                FontWeight.Bold
                        )
                    }

                    Spacer(
                        modifier =
                            Modifier.height(16.dp)
                    )

                    Text(

                        text = sowingText,

                        fontSize = 18.sp,

                        fontWeight =
                            FontWeight.Medium
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            // WEATHER TITLE

            Text(

                text = weatherTitle,

                fontSize = 22.sp,

                fontWeight =
                    FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            val weatherList = listOf(

                Pair(
                    if (isKannada)
                        "🌡 ತಾಪಮಾನ"
                    else
                        "🌡 Temperature",

                    temperature
                ),

                Pair(
                    if (isKannada)
                        "💧 ಆರ್ದ್ರತೆ"
                    else
                        "💧 Humidity",

                    humidity
                ),

                Pair(
                    if (isKannada)
                        "☁ ಹವಾಮಾನ"
                    else
                        "☁ Condition",

                    condition
                ),

                Pair(
                    if (isKannada)
                        "🌧 ಮಳೆ"
                    else
                        "🌧 Rain",

                    rainChance
                )
            )

            LazyVerticalGrid(

                columns = GridCells.Fixed(2),

                modifier =
                    Modifier.height(250.dp),

                verticalArrangement =
                    Arrangement.spacedBy(12.dp),

                horizontalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {

                items(weatherList) { item ->

                    Card(

                        shape =
                            RoundedCornerShape(24.dp)
                    ) {

                        Column(

                            modifier =
                                Modifier.padding(18.dp)
                        ) {

                            Text(

                                text = item.first,

                                fontWeight =
                                    FontWeight.Bold
                            )

                            Spacer(
                                modifier =
                                    Modifier.height(10.dp)
                            )

                            Text(

                                text = item.second,

                                fontSize = 22.sp
                            )
                        }
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            // RECOMMENDATION CARD

            Card(

                colors =
                    CardDefaults.cardColors(
                        containerColor =
                            Color(0xFFE8F5E9)
                    ),

                shape =
                    RoundedCornerShape(24.dp)
            ) {

                Column(

                    modifier =
                        Modifier.padding(20.dp)
                ) {

                    Text(

                        text =
                            recommendationTitle,

                        fontWeight =
                            FontWeight.Bold,

                        fontSize = 20.sp
                    )

                    Spacer(
                        modifier =
                            Modifier.height(12.dp)
                    )

                    Text(

                        text = recommendation,

                        fontSize = 18.sp
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            // FARMER INSIGHTS

            Text(

                text =

                    if (isKannada)
                        "🚜 ರೈತ ಮಾಹಿತಿ"
                    else
                        "🚜 Farmer Insights",

                fontSize = 22.sp,

                fontWeight =
                    FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            val insights = listOf(

                if (isKannada)
                    "🌾 ಹತ್ತಿಗೆ ಉತ್ತಮ ಹವಾಮಾನ"
                else
                    "🌾 Good climate for cotton",

                if (isKannada)
                    "🛰 ಮುಂದಿನ 3 ದಿನ ಮಳೆಯ ಸಾಧ್ಯತೆ"
                else
                    "🛰 Rain expected in next 3 days",

                if (isKannada)
                    "💧 ನೀರಾವರಿ ಮಟ್ಟ ಸರಿಯಾಗಿದೆ"
                else
                    "💧 Irrigation level is stable",

                if (isKannada)
                    "📈 ಮಾರುಕಟ್ಟೆ ಬೆಲೆ ಏರಿಕೆ"
                else
                    "📈 Market price increasing"
            )

            insights.forEach { item ->

                Card(

                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),

                    shape =
                        RoundedCornerShape(20.dp)
                ) {

                    Text(

                        text = item,

                        modifier =
                            Modifier.padding(18.dp),

                        fontSize = 17.sp
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(100.dp)
            )
        }
    }
}