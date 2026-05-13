package com.example.raitha_bharosa_hub.ui.screens.soil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Science
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.raitha_bharosa_hub.data.local.AppDatabase
import com.example.raitha_bharosa_hub.data.local.SoilEntity
import com.example.raitha_bharosa_hub.utils.LocaleHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SoilAnalysisScreen() {

    val context = LocalContext.current

    val db =
        AppDatabase.getInstance(context)

    val dao = db.soilDao()

    val scope =
        rememberCoroutineScope()

    var isKannada by remember {

        mutableStateOf(
            LocaleHelper.getLanguage(context) == "kn"
        )
    }

    var nitrogen by remember {
        mutableStateOf("")
    }

    var phosphorus by remember {
        mutableStateOf("")
    }

    var potassium by remember {
        mutableStateOf("")
    }

    var result by remember {
        mutableStateOf("")
    }

    var fertilizer by remember {
        mutableStateOf("")
    }

    var crop by remember {
        mutableStateOf("")
    }

    var history by remember {

        mutableStateOf<List<SoilEntity>>(
            emptyList()
        )
    }

    // LOAD HISTORY

    LaunchedEffect(Unit) {

        scope.launch(Dispatchers.IO) {

            history =
                dao.getAllHistory()
        }
    }

    Box(

        modifier = Modifier
            .fillMaxSize()
            .background(

                Brush.verticalGradient(

                    listOf(
                        Color(0xFFF1F8E9),
                        Color.White
                    )
                )
            )
    ) {

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(
                    rememberScrollState()
                ),

            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Text(

                text =
                    if (isKannada)
                        "🌱 ಮಣ್ಣಿನ ವಿಶ್ಲೇಷಣೆ"
                    else
                        "🌱 Soil Analysis",

                fontSize = 28.sp,

                fontWeight =
                    FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(30.dp)
            )

            SoilField(

                value = nitrogen,

                onValueChange = {

                    nitrogen = it
                },

                label =
                    if (isKannada)
                        "ನೈಟ್ರೋಜನ್ (N)"
                    else
                        "Nitrogen (N)"
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            SoilField(

                value = phosphorus,

                onValueChange = {

                    phosphorus = it
                },

                label =
                    if (isKannada)
                        "ಫಾಸ್ಫರಸ್ (P)"
                    else
                        "Phosphorus (P)"
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            SoilField(

                value = potassium,

                onValueChange = {

                    potassium = it
                },

                label =
                    if (isKannada)
                        "ಪೊಟ್ಯಾಸಿಯಮ್ (K)"
                    else
                        "Potassium (K)"
            )

            Spacer(
                modifier = Modifier.height(30.dp)
            )

            Button(

                onClick = {

                    val n =
                        nitrogen.toIntOrNull() ?: 0

                    val p =
                        phosphorus.toIntOrNull() ?: 0

                    val k =
                        potassium.toIntOrNull() ?: 0

                    // ADVANCED ANALYSIS

                    when {

                        n < 40 && p < 40 && k < 40 -> {

                            result =
                                if (isKannada)
                                    "NPK ಎಲ್ಲಾ ಮೌಲ್ಯಗಳು ಕಡಿಮೆ"
                                else
                                    "All NPK values are low"

                            crop =
                                if (isKannada)
                                    "ರಾಗಿ / ಕಡಲೆ"
                                else
                                    "Ragi / Groundnut"

                            fertilizer =
                                if (isKannada)
                                    "NPK ಹಾಗೂ ಸಾವಯವ ಗೊಬ್ಬರ ಸೇರಿಸಿ"
                                else
                                    "Add NPK and organic manure"
                        }

                        n > 90 && p > 70 && k > 70 -> {

                            result =
                                if (isKannada)
                                    "ಮಣ್ಣು ಅತ್ಯಂತ ಫಲವತ್ತಾಗಿದೆ"
                                else
                                    "Soil is extremely fertile"

                            crop =
                                if (isKannada)
                                    "ಅಕ್ಕಿ / ಕಬ್ಬು"
                                else
                                    "Rice / Sugarcane"

                            fertilizer =
                                if (isKannada)
                                    "ಹೆಚ್ಚು ಗೊಬ್ಬರ ಬೇಡ"
                                else
                                    "Avoid excess fertilizer"
                        }

                        n < 40 -> {

                            result =
                                if (isKannada)
                                    "ನೈಟ್ರೋಜನ್ ಕಡಿಮೆ"
                                else
                                    "Nitrogen is low"

                            crop =
                                if (isKannada)
                                    "ರಾಗಿ"
                                else
                                    "Ragi"

                            fertilizer =
                                if (isKannada)
                                    "ಯೂರಿಯಾ ಸೇರಿಸಿ"
                                else
                                    "Add Urea"
                        }

                        p < 40 -> {

                            result =
                                if (isKannada)
                                    "ಫಾಸ್ಫರಸ್ ಕಡಿಮೆ"
                                else
                                    "Phosphorus is low"

                            crop =
                                if (isKannada)
                                    "ಬೇಳೆ"
                                else
                                    "Pulses"

                            fertilizer =
                                if (isKannada)
                                    "SSP/DAP ಬಳಸಿ"
                                else
                                    "Use SSP/DAP"
                        }

                        k < 40 -> {

                            result =
                                if (isKannada)
                                    "ಪೊಟ್ಯಾಸಿಯಮ್ ಕಡಿಮೆ"
                                else
                                    "Potassium is low"

                            crop =
                                if (isKannada)
                                    "ಬಾಳೆ"
                                else
                                    "Banana"

                            fertilizer =
                                if (isKannada)
                                    "MOP ಸೇರಿಸಿ"
                                else
                                    "Add MOP fertilizer"
                        }

                        n > 100 -> {

                            result =
                                if (isKannada)
                                    "ನೈಟ್ರೋಜನ್ ಹೆಚ್ಚು"
                                else
                                    "Nitrogen is high"

                            crop =
                                if (isKannada)
                                    "ಹತ್ತಿ"
                                else
                                    "Cotton"

                            fertilizer =
                                if (isKannada)
                                    "ಯೂರಿಯಾ ಕಡಿಮೆ ಮಾಡಿ"
                                else
                                    "Reduce urea usage"
                        }

                        else -> {

                            result =
                                if (isKannada)
                                    "ಮಣ್ಣು ಸರಾಸರಿ ಮಟ್ಟದಲ್ಲಿದೆ"
                                else
                                    "Soil condition is average"

                            crop =
                                if (isKannada)
                                    "ಹತ್ತಿ"
                                else
                                    "Cotton"

                            fertilizer =
                                if (isKannada)
                                    "ಸಮತೋಲನ NPK ಬಳಸಿ"
                                else
                                    "Use balanced NPK mix"
                        }
                    }

                    // SAVE HISTORY

                    scope.launch(
                        Dispatchers.IO
                    ) {

                        dao.insertSoil(

                            SoilEntity(

                                nitrogen = nitrogen,

                                phosphorus = phosphorus,

                                potassium = potassium,

                                result = result,

                                crop = crop,

                                fertilizer = fertilizer
                            )
                        )

                        history =
                            dao.getAllHistory()
                    }
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

                Icon(
                    Icons.Default.Science,
                    contentDescription = null
                )

                Spacer(
                    modifier = Modifier.width(10.dp)
                )

                Text(

                    text =
                        if (isKannada)
                            "ವಿಶ್ಲೇಷಿಸಿ"
                        else
                            "Analyze"
                )
            }

            Spacer(
                modifier = Modifier.height(30.dp)
            )

            // RESULT CARD

            if (result.isNotEmpty()) {

                Card(

                    modifier =
                        Modifier.fillMaxWidth(),

                    shape =
                        RoundedCornerShape(24.dp)
                ) {

                    Column(

                        modifier =
                            Modifier.padding(22.dp)
                    ) {

                        Text(

                            text =
                                if (isKannada)
                                    "📊 ವರದಿ"
                                else
                                    "📊 Analysis Report",

                            fontWeight =
                                FontWeight.Bold,

                            fontSize = 22.sp
                        )

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )

                        Text("✅ $result")

                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )

                        Text(

                            text =
                                if (isKannada)
                                    "🌾 ಶಿಫಾರಸು ಬೆಳೆ: $crop"
                                else
                                    "🌾 Recommended Crop: $crop"
                        )

                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )

                        Text(

                            text =
                                if (isKannada)
                                    "🧪 ರಸಗೊಬ್ಬರ ಸಲಹೆ: $fertilizer"
                                else
                                    "🧪 Fertilizer Suggestion: $fertilizer"
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(30.dp)
            )

            // HISTORY TITLE

            Text(

                text =
                    if (isKannada)
                        "📜 ಹಿಂದಿನ ವಿಶ್ಲೇಷಣೆಗಳು"
                    else
                        "📜 Analysis History",

                fontSize = 24.sp,

                fontWeight =
                    FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            // HISTORY LIST

            history.reversed().forEach { item ->

                Card(

                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 14.dp),

                    shape =
                        RoundedCornerShape(20.dp)
                ) {

                    Column(

                        modifier =
                            Modifier.padding(18.dp)
                    ) {

                        Text(
                            text =
                                "N: ${item.nitrogen}"
                        )

                        Text(
                            text =
                                "P: ${item.phosphorus}"
                        )

                        Text(
                            text =
                                "K: ${item.potassium}"
                        )

                        Spacer(
                            modifier =
                                Modifier.height(8.dp)
                        )

                        Text(
                            text = item.result
                        )

                        Text(
                            text = item.crop
                        )

                        Text(
                            text = item.fertilizer
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(100.dp)
            )
        }
    }
}

@Composable
fun SoilField(

    value: String,

    onValueChange: (String) -> Unit,

    label: String
) {

    OutlinedTextField(

        value = value,

        onValueChange = {

            onValueChange(it)
        },

        label = {

            Text(label)
        },

        keyboardOptions =
            KeyboardOptions(
                keyboardType =
                    KeyboardType.Number
            ),

        modifier =
            Modifier.fillMaxWidth(),

        shape =
            RoundedCornerShape(18.dp)
    )
}