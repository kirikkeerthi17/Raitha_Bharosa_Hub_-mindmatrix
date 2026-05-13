package com.example.raitha_bharosa_hub.ui.screens.settings

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.raitha_bharosa_hub.data.local.AppDatabase
import com.example.raitha_bharosa_hub.data.local.UserEntity
import com.example.raitha_bharosa_hub.utils.LocaleHelper
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun SettingsScreen(

    userName: String,

    onLogout: () -> Unit
) {

    val context =
        LocalContext.current

    val scope =
        rememberCoroutineScope()

    val db =
        AppDatabase.getInstance(context)

    val dao =
        db.userDao()

    var isKannada by remember {

        mutableStateOf(

            LocaleHelper
                .getLanguage(context) == "kn"
        )
    }

    var profileName by remember {

        mutableStateOf(userName)
    }

    var selectedCrop by remember {

        mutableStateOf("Cotton")
    }

    val cropList = listOf(

        "Cotton",
        "Rice",
        "Ragi",
        "Sugarcane",
        "Groundnut",
        "Maize",
        "Wheat",
        "Tomato"
    )

    var expanded by remember {

        mutableStateOf(false)
    }

    val userEmail =

        FirebaseAuth
            .getInstance()
            .currentUser
            ?.email
            ?: "Farmer"

    Box(

        modifier = Modifier
            .fillMaxSize()
            .background(

                Brush.verticalGradient(

                    listOf(
                        Color(0xFFF4F8F5),
                        Color.White
                    )
                )
            )
    ) {

        Column(

            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(20.dp),

            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            // TITLE

            Text(

                text =

                    if (isKannada)
                        "⚙ ಸೆಟ್ಟಿಂಗ್ಸ್"
                    else
                        "⚙ Settings",

                fontSize = 30.sp,

                fontWeight =
                    FontWeight.Bold,

                color = Color(0xFF1B5E20)
            )

            Spacer(
                modifier = Modifier.height(30.dp)
            )

            // PROFILE CARD

            Card(

                modifier =
                    Modifier.fillMaxWidth(),

                shape =
                    RoundedCornerShape(30.dp),

                colors =
                    CardDefaults.cardColors(
                        containerColor =
                            Color.White
                    )
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

                        modifier =
                            Modifier
                                .size(90.dp)
                                .clip(CircleShape)
                                .background(
                                    Color(0xFFE8F5E9)
                                ),

                        contentAlignment =
                            Alignment.Center
                    ) {

                        Icon(

                            Icons.Default.Person,

                            contentDescription = null,

                            tint =
                                Color(0xFF2E7D32),

                            modifier =
                                Modifier.size(50.dp)
                        )
                    }

                    Spacer(
                        modifier =
                            Modifier.height(16.dp)
                    )

                    // PROFILE NAME

                    OutlinedTextField(

                        value = profileName,

                        onValueChange = {

                            profileName = it
                        },

                        label = {

                            Text(

                                if (isKannada)
                                    "ಪ್ರೊಫೈಲ್ ಹೆಸರು"
                                else
                                    "Profile Name"
                            )
                        },

                        modifier =
                            Modifier.fillMaxWidth(),

                        shape =
                            RoundedCornerShape(18.dp)
                    )

                    Spacer(
                        modifier =
                            Modifier.height(16.dp)
                    )

                    // CROP SELECTOR

                    ExposedDropdownMenuBox(

                        expanded = expanded,

                        onExpandedChange = {

                            expanded = !expanded
                        }
                    ) {

                        OutlinedTextField(

                            value = selectedCrop,

                            onValueChange = {},

                            readOnly = true,

                            label = {

                                Text(

                                    if (isKannada)
                                        "ಬೆಳೆ"
                                    else
                                        "Selected Crop"
                                )
                            },

                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .menuAnchor(),

                            shape =
                                RoundedCornerShape(18.dp)
                        )

                        ExposedDropdownMenu(

                            expanded = expanded,

                            onDismissRequest = {

                                expanded = false
                            }
                        ) {

                            cropList.forEach { crop ->

                                DropdownMenuItem(

                                    text = {

                                        Text(crop)
                                    },

                                    onClick = {

                                        selectedCrop = crop

                                        expanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(
                        modifier =
                            Modifier.height(16.dp)
                    )

                    Text(

                        text = userEmail,

                        color = Color.Gray
                    )

                    Spacer(
                        modifier =
                            Modifier.height(20.dp)
                    )

                    // SAVE BUTTON

                    Button(

                        onClick = {

                            scope.launch(
                                Dispatchers.IO
                            ) {

                                dao.insertUser(

                                    UserEntity(

                                        name = profileName,

                                        crop = selectedCrop,

                                        language =

                                            if (isKannada)
                                                "Kannada"
                                            else
                                                "English"
                                    )
                                )
                            }
                        },

                        modifier =
                            Modifier.fillMaxWidth(),

                        shape =
                            RoundedCornerShape(18.dp),

                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor =
                                    Color(0xFF2E7D32)
                            )
                    ) {

                        Text(

                            text =

                                if (isKannada)
                                    "ಉಳಿಸಿ"
                                else
                                    "Save"
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            // LANGUAGE CARD

            Card(

                modifier =
                    Modifier.fillMaxWidth(),

                shape =
                    RoundedCornerShape(28.dp)
            ) {

                Row(

                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(22.dp),

                    horizontalArrangement =
                        Arrangement.SpaceBetween,

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Row(

                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Icon(

                            Icons.Default.Language,

                            contentDescription = null,

                            tint =
                                Color(0xFF2E7D32)
                        )

                        Spacer(
                            modifier =
                                Modifier.width(12.dp)
                        )

                        Column {

                            Text(

                                text =

                                    if (isKannada)
                                        "ಭಾಷೆ"
                                    else
                                        "Language",

                                fontWeight =
                                    FontWeight.Bold,

                                fontSize = 18.sp
                            )

                            Spacer(
                                modifier =
                                    Modifier.height(4.dp)
                            )

                            Text(

                                text =

                                    if (isKannada)
                                        "ಕನ್ನಡ"
                                    else
                                        "English",

                                color = Color.Gray
                            )
                        }
                    }

                    Switch(

                        checked = isKannada,

                        onCheckedChange = {

                            isKannada = it

                            LocaleHelper.setLocale(

                                context as Activity,

                                if (it)
                                    "kn"
                                else
                                    "en"
                            )
                        }
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            // APP INFO

            Card(

                modifier =
                    Modifier.fillMaxWidth(),

                shape =
                    RoundedCornerShape(28.dp)
            ) {

                Column(

                    modifier =
                        Modifier.padding(22.dp)
                ) {

                    Text(

                        text =
                            "🌾 Raitha Bharosa Hub",

                        fontWeight =
                            FontWeight.Bold,

                        fontSize = 22.sp
                    )

                    Spacer(
                        modifier =
                            Modifier.height(10.dp)
                    )

                    Text(

                        text =
                            "Healthy academic behaviour insights",

                        color = Color.Gray
                    )

                    Spacer(
                        modifier =
                            Modifier.height(14.dp)
                    )

                    Text(

                        text =
                            "Version 1.0.0",

                        color =
                            Color(0xFF2E7D32),

                        fontWeight =
                            FontWeight.SemiBold
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(30.dp)
            )

            // LOGOUT BUTTON

            Button(

                onClick = {

                    FirebaseAuth
                        .getInstance()
                        .signOut()

                    onLogout()
                },

                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(58.dp),

                shape =
                    RoundedCornerShape(20.dp),

                colors =
                    ButtonDefaults.buttonColors(
                        containerColor =
                            Color(0xFFD32F2F)
                    )
            ) {

                Icon(

                    Icons.Default.Logout,

                    contentDescription = null
                )

                Spacer(
                    modifier = Modifier.width(10.dp)
                )

                Text(

                    text =

                        if (isKannada)
                            "ಲಾಗ್ ಔಟ್"
                        else
                            "Logout",

                    fontSize = 18.sp
                )
            }

            Spacer(
                modifier = Modifier.height(100.dp)
            )
        }
    }
}