package com.example.raitha_bharosa_hub.ui.screens.onboarding

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Language
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
import com.example.raitha_bharosa_hub.data.local.UserEntity
import com.example.raitha_bharosa_hub.utils.LocaleHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(

    saveUser: (UserEntity) -> Unit,

    onDone: () -> Unit
) {

    val context = LocalContext.current

    // LANGUAGE

    var isKannada by remember {

        mutableStateOf(
            LocaleHelper.getLanguage(context) == "kn"
        )
    }

    // TEXTS

    val appTitle =
        if (isKannada)
            "🌾 ರೈತ ಭರೋಸಾ ಹಬ್"
        else
            "🌾 Raitha Bharosa Hub"

    val subtitle =
        if (isKannada)
            "ಪ್ರತಿ ರೈತನಿಗೂ ಸ್ಮಾರ್ಟ್ ಕೃಷಿ ಸಹಾಯಕ"
        else
            "Smart farming assistant for every farmer"

    val farmerNameText =
        if (isKannada)
            "ರೈತನ ಹೆಸರು"
        else
            "Farmer Name"

    val selectCropText =
        if (isKannada)
            "ಬೆಳೆ ಆಯ್ಕೆಮಾಡಿ"
        else
            "Select Crop"

    val chooseLangText =
        if (isKannada)
            "ಭಾಷೆ ಆಯ್ಕೆಮಾಡಿ"
        else
            "Choose Language"

    val continueText =
        if (isKannada)
            "ಮುಂದುವರಿಸಿ"
        else
            "Continue"

    // USER INPUTS

    var name by remember {

        mutableStateOf("")
    }

    var selectedCrop by remember {

        mutableStateOf("Cotton")
    }

    var expanded by remember {

        mutableStateOf(false)
    }

    val crops = listOf(

        "Cotton",
        "Rice",
        "Wheat",
        "Sugarcane",
        "Maize",
        "Ragi",
        "Groundnut",
        "Tomato"
    )

    Box(

        modifier = Modifier
            .fillMaxSize()
            .background(

                Brush.verticalGradient(

                    listOf(
                        Color(0xFFF1F8E9),
                        Color(0xFFE8F5E9),
                        Color.White
                    )
                )
            )
    ) {

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),

            horizontalAlignment =
                Alignment.CenterHorizontally,

            verticalArrangement =
                Arrangement.Center
        ) {

            // TITLE

            Text(

                text = appTitle,

                fontSize = 30.sp,

                fontWeight = FontWeight.Bold,

                color = Color(0xFF1B5E20)
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(

                text = subtitle,

                color = Color.Gray,

                fontSize = 15.sp
            )

            Spacer(
                modifier = Modifier.height(40.dp)
            )

            // MAIN CARD

            Card(

                modifier =
                    Modifier.fillMaxWidth(),

                shape =
                    RoundedCornerShape(30.dp),

                elevation =
                    CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    ),

                colors =
                    CardDefaults.cardColors(
                        containerColor = Color.White
                    )
            ) {

                Column(

                    modifier =
                        Modifier.padding(22.dp)
                ) {

                    // NAME FIELD

                    OutlinedTextField(

                        value = name,

                        onValueChange = {

                            name = it
                        },

                        label = {

                            Text(farmerNameText)
                        },

                        modifier =
                            Modifier.fillMaxWidth(),

                        shape =
                            RoundedCornerShape(18.dp)
                    )

                    Spacer(
                        modifier = Modifier.height(22.dp)
                    )

                    // CROP DROPDOWN

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

                                Text(selectCropText)
                            },

                            trailingIcon = {

                                Icon(

                                    Icons.Default.ArrowDropDown,

                                    contentDescription = null
                                )
                            },

                            modifier =
                                Modifier
                                    .menuAnchor()
                                    .fillMaxWidth(),

                            shape =
                                RoundedCornerShape(18.dp)
                        )

                        ExposedDropdownMenu(

                            expanded = expanded,

                            onDismissRequest = {

                                expanded = false
                            }
                        ) {

                            crops.forEach { crop ->

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
                        modifier = Modifier.height(26.dp)
                    )

                    // LANGUAGE

                    Text(

                        text = chooseLangText,

                        fontWeight = FontWeight.Bold,

                        color = Color.DarkGray
                    )

                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )

                    Row(

                        horizontalArrangement =
                            Arrangement.spacedBy(14.dp)
                    ) {

                        Button(

                            onClick = {

                                isKannada = false

                                LocaleHelper.setLocale(

                                    context as Activity,

                                    "en"
                                )
                            },

                            colors =
                                ButtonDefaults.buttonColors(
                                    containerColor =
                                        if (!isKannada)
                                            Color(0xFF2E7D32)
                                        else
                                            Color.Gray
                                ),

                            shape =
                                RoundedCornerShape(16.dp)
                        ) {

                            Icon(
                                Icons.Default.Language,
                                contentDescription = null
                            )

                            Spacer(
                                modifier =
                                    Modifier.width(6.dp)
                            )

                            Text("English")
                        }

                        Button(

                            onClick = {

                                isKannada = true

                                LocaleHelper.setLocale(

                                    context as Activity,

                                    "kn"
                                )
                            },

                            colors =
                                ButtonDefaults.buttonColors(
                                    containerColor =
                                        if (isKannada)
                                            Color(0xFF2E7D32)
                                        else
                                            Color.Gray
                                ),

                            shape =
                                RoundedCornerShape(16.dp)
                        ) {

                            Icon(
                                Icons.Default.Language,
                                contentDescription = null
                            )

                            Spacer(
                                modifier =
                                    Modifier.width(6.dp)
                            )

                            Text("ಕನ್ನಡ")
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(34.dp)
                    )

                    // CONTINUE BUTTON

                    Button(

                        onClick = {

                            saveUser(

                                UserEntity(

                                    name = name,

                                    crop = selectedCrop,

                                    language =
                                        if (isKannada)
                                            "Kannada"
                                        else
                                            "English"
                                )
                            )

                            onDone()
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
                                    Color(0xFF2E7D32)
                            )
                    ) {

                        Text(

                            text = continueText,

                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}