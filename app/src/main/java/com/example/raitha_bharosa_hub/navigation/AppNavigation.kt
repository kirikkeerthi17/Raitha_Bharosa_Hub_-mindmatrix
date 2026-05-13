package com.example.raitha_bharosa_hub.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.raitha_bharosa_hub.ui.screens.calender.KrishiCalendarScreen
import com.example.raitha_bharosa_hub.ui.screens.chat.ChatScreen
import com.example.raitha_bharosa_hub.ui.screens.dashboard.DashboardScreen
import com.example.raitha_bharosa_hub.ui.screens.settings.SettingsScreen
import com.example.raitha_bharosa_hub.ui.screens.soil.SoilAnalysisScreen

@Composable
fun AppNavigation(

    userName: String,

    cropName: String
) {

    val navController =
        rememberNavController()

    NavHost(

        navController = navController,

        startDestination = "dashboard"
    ) {

        // DASHBOARD

        composable("dashboard") {

            DashboardScreen(

                userName = userName,

                cropName = cropName,

                onSoilClick = {

                    navController.navigate(
                        "soil"
                    )
                },

                onKrishiClick = {

                    navController.navigate(
                        "krishi"
                    )
                },

                onSettingsClick = {

                    navController.navigate(
                        "settings"
                    )
                },

                onChatClick = {

                    navController.navigate(
                        "chat"
                    )
                }
            )
        }

        // SOIL ANALYTICS

        composable("soil") {

            SoilAnalysisScreen()
        }

        // KRISHI CALENDAR

        composable("krishi") {

            KrishiCalendarScreen()
        }

        // SETTINGS

        composable("settings") {

            SettingsScreen(

                userName = userName,

                onLogout = {

                    navController.navigate(
                        "dashboard"
                    ) {

                        popUpTo(0)
                    }
                }
            )
        }

        // CHATBOT

        composable("chat") {

            ChatScreen()
        }
    }
}