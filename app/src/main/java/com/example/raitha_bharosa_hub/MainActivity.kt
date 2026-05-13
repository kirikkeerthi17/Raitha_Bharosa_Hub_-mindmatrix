package com.example.raitha_bharosa_hub

import android.os.Bundle

import com.example.raitha_bharosa_hub.navigation.AppNavigation

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.raitha_bharosa_hub.data.local.AppDatabase
import com.example.raitha_bharosa_hub.data.local.UserEntity
import com.example.raitha_bharosa_hub.ui.screens.auth.LoginScreen
import com.example.raitha_bharosa_hub.ui.screens.dashboard.DashboardScreen
import com.example.raitha_bharosa_hub.ui.screens.onboarding.OnboardingScreen
import com.example.raitha_bharosa_hub.utils.LocaleHelper
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        // APPLY SAVED LANGUAGE
        LocaleHelper.applyLanguage(this)

        super.onCreate(savedInstanceState)

        val db = AppDatabase.getInstance(this)

        val dao = db.userDao()

        setContent {

            Surface(
                color = MaterialTheme.colorScheme.background
            ) {

                val auth = FirebaseAuth.getInstance()

                var screen by remember {
                    mutableStateOf("loading")
                }

                var user by remember {
                    mutableStateOf<UserEntity?>(null)
                }

                val scope = rememberCoroutineScope()

                // LOAD USER
                LaunchedEffect(Unit) {

                    val loadedUser =
                        withContext(Dispatchers.IO) {

                            dao.getUser()
                        }

                    user = loadedUser

                    screen =

                        if (auth.currentUser == null) {

                            "login"

                        } else {

                            if (loadedUser == null)
                                "onboarding"
                            else
                                "dashboard"
                        }
                }

                when (screen) {

                    // LOADING
                    "loading" -> {

                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {

                            CircularProgressIndicator()
                        }
                    }

                    // LOGIN
                    "login" -> LoginScreen {

                        scope.launch {

                            val loadedUser =
                                withContext(Dispatchers.IO) {

                                    dao.getUser()
                                }

                            user = loadedUser

                            screen =

                                if (loadedUser == null)
                                    "onboarding"
                                else
                                    "dashboard"
                        }
                    }

                    // ONBOARDING
                    "onboarding" -> OnboardingScreen(

                        saveUser = {

                            scope.launch(Dispatchers.IO) {

                                dao.insertUser(it)
                            }
                        },

                        onDone = {

                            scope.launch {

                                val loadedUser =
                                    withContext(Dispatchers.IO) {

                                        dao.getUser()
                                    }

                                user = loadedUser

                                screen = "dashboard"
                            }
                        }
                    )

                    // DASHBOARD
                    "dashboard" -> AppNavigation(

                        userName =
                            user?.name ?: "Farmer",

                        cropName =
                            user?.crop ?: "Cotton"
                    )
                }
            }
        }
    }
}