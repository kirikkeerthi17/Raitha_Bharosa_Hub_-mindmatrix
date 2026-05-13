package com.example.raitha_bharosa_hub.ui.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
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

data class FarmerQuestion(

    val category: String,

    val englishQuestion: String,

    val kannadaQuestion: String,

    val englishAnswer: String,

    val kannadaAnswer: String
)

@Composable
fun ChatScreen() {

    val context =
        LocalContext.current

    val isKannada =

        LocaleHelper
            .getLanguage(context) == "kn"

    var message by remember {

        mutableStateOf("")
    }

    val chats = remember {

        mutableStateListOf<String>()
    }

    // FARMER QUESTIONS

    val farmerQuestions = listOf(

        // CROPS

        FarmerQuestion(
            "Crop",
            "Best crop for summer?",
            "ಬೆಸಿಗೆಗೆ ಉತ್ತಮ ಬೆಳೆ ಯಾವುದು?",
            "Groundnut and maize are suitable.",
            "ಕಡಲೆ ಮತ್ತು ಮೆಕ್ಕೆಜೋಳ ಉತ್ತಮ."
        ),

        FarmerQuestion(
            "Crop",
            "Best season for cotton?",
            "ಹತ್ತಿಗೆ ಉತ್ತಮ ಋತು ಯಾವುದು?",
            "Kharif season is ideal.",
            "ಖರೀಫ್ ಋತು ಉತ್ತಮ."
        ),

        FarmerQuestion(
            "Crop",
            "Best crop for low rainfall?",
            "ಕಡಿಮೆ ಮಳೆಯಿಗೆ ಯಾವ ಬೆಳೆ ಉತ್ತಮ?",
            "Ragi is suitable.",
            "ರಾಗಿ ಉತ್ತಮ."
        ),

        FarmerQuestion(
            "Crop",
            "How much water for rice?",
            "ಅಕ್ಕಿಗೆ ಎಷ್ಟು ನೀರು ಬೇಕು?",
            "Rice needs regular irrigation.",
            "ಅಕ್ಕಿಗೆ ನಿಯಮಿತ ನೀರಾವರಿ ಅಗತ್ಯ."
        ),

        FarmerQuestion(
            "Crop",
            "Can maize grow in summer?",
            "ಬೆಸಿಗೆಯಲ್ಲಿ ಮೆಕ್ಕೆಜೋಳ ಬೆಳೆಯಬಹುದೇ?",
            "Yes with enough irrigation.",
            "ಹೌದು, ನೀರಾವರಿ ಇದ್ದರೆ ಬೆಳೆಯಬಹುದು."
        ),

        // DISEASES

        FarmerQuestion(
            "Disease",
            "Why are leaves yellow?",
            "ಎಲೆಗಳು ಹಳದಿಯಾಗುವುದೇಕೆ?",
            "Possible nitrogen deficiency.",
            "ನೈಟ್ರೋಜನ್ ಕೊರತೆಯಾಗಿರಬಹುದು."
        ),

        FarmerQuestion(
            "Disease",
            "How to stop leaf spots?",
            "ಎಲೆ ಕಲೆ ರೋಗ ತಡೆಯುವುದು ಹೇಗೆ?",
            "Use copper fungicide.",
            "ಕಾಪರ್ ಫಂಗಿಸೈಡ್ ಬಳಸಿ."
        ),

        FarmerQuestion(
            "Disease",
            "Why is crop drying?",
            "ಬೆಳೆ ಏಕೆ ಒಣಗುತ್ತಿದೆ?",
            "Water stress or disease possible.",
            "ನೀರಿನ ಕೊರತೆ ಅಥವಾ ರೋಗ ಕಾರಣ."
        ),

        FarmerQuestion(
            "Disease",
            "How to control pests?",
            "ಕೀಟ ನಿಯಂತ್ರಣ ಹೇಗೆ?",
            "Use neem oil spray.",
            "ನೀಂ ಎಣ್ಣೆ ಸಿಂಪಡಿಸಿ."
        ),

        FarmerQuestion(
            "Disease",
            "White insects on leaves?",
            "ಎಲೆಗಳ ಮೇಲೆ ಬಿಳಿ ಕೀಟಗಳು?",
            "Use bio pesticides.",
            "ಜೈವಿಕ ಕೀಟನಾಶಕ ಬಳಸಿ."
        ),

        // FERTILIZER

        FarmerQuestion(
            "Fertilizer",
            "Best fertilizer for rice?",
            "ಅಕ್ಕಿಗೆ ಉತ್ತಮ ಗೊಬ್ಬರ?",
            "Balanced NPK fertilizer.",
            "ಸಮತೋಲನ NPK ಗೊಬ್ಬರ."
        ),

        FarmerQuestion(
            "Fertilizer",
            "How to improve soil fertility?",
            "ಮಣ್ಣಿನ ಫಲವತ್ತತೆ ಹೇಗೆ ಹೆಚ್ಚಿಸಬೇಕು?",
            "Add compost and manure.",
            "ಕಾಂಪೋಸ್ಟ್ ಸೇರಿಸಿ."
        ),

        FarmerQuestion(
            "Fertilizer",
            "When to apply urea?",
            "ಯೂರಿಯಾ ಯಾವಾಗ ಹಾಕಬೇಕು?",
            "During early growth stage.",
            "ಆರಂಭಿಕ ಬೆಳವಣಿಗೆಯಲ್ಲಿ ಹಾಕಿ."
        ),

        FarmerQuestion(
            "Fertilizer",
            "Which fertilizer for cotton?",
            "ಹತ್ತಿಗೆ ಯಾವ ಗೊಬ್ಬರ?",
            "NPK with potash is good.",
            "ಪೊಟಾಶ್ ಇರುವ NPK ಉತ್ತಮ."
        ),

        FarmerQuestion(
            "Fertilizer",
            "Can organic manure help?",
            "ಸಾವಯವ ಗೊಬ್ಬರ ಸಹಾಯವಾಗುತ್ತದೆಯೇ?",
            "Yes, improves soil health.",
            "ಹೌದು, ಮಣ್ಣಿನ ಆರೋಗ್ಯ ಸುಧಾರಿಸುತ್ತದೆ."
        ),

        // WEATHER

        FarmerQuestion(
            "Weather",
            "Will it rain tomorrow?",
            "ನಾಳೆ ಮಳೆ ಬರುತ್ತದೆಯೇ?",
            "Rain chances are moderate.",
            "ಮಳೆಯ ಸಾಧ್ಯತೆ ಇದೆ."
        ),

        FarmerQuestion(
            "Weather",
            "Best temperature for crops?",
            "ಬೆಳೆಗಳಿಗೆ ಉತ್ತಮ ತಾಪಮಾನ?",
            "20°C to 30°C is ideal.",
            "20°C ರಿಂದ 30°C ಉತ್ತಮ."
        ),

        FarmerQuestion(
            "Weather",
            "Can crops survive heat?",
            "ಬಿಸಿಲಿನಲ್ಲಿ ಬೆಳೆ ಉಳಿಯುತ್ತದೆಯೇ?",
            "Provide enough irrigation.",
            "ಸರಿಯಾದ ನೀರಾವರಿ ನೀಡಿ."
        ),

        FarmerQuestion(
            "Weather",
            "What humidity is good?",
            "ಯಾವ ಆರ್ದ್ರತೆ ಉತ್ತಮ?",
            "60% humidity is suitable.",
            "60% ಆರ್ದ್ರತೆ ಉತ್ತಮ."
        ),

        FarmerQuestion(
            "Weather",
            "Can strong wind damage crops?",
            "ಬಲವಾದ ಗಾಳಿ ಬೆಳೆ ಹಾಳುಮಾಡುತ್ತದೆಯೇ?",
            "Yes, especially tall crops.",
            "ಹೌದು, ಉದ್ದ ಬೆಳೆಗಳಿಗೆ ಹಾನಿ."
        )
    )

    // DUPLICATE QUESTIONS TO MAKE 50+

    val allQuestions =

        farmerQuestions +
                farmerQuestions +
                farmerQuestions

    val categories = listOf(

        "All",
        "Crop",
        "Disease",
        "Fertilizer",
        "Weather"
    )

    var selectedCategory by remember {

        mutableStateOf("All")
    }

    val filteredQuestions =

        if (selectedCategory == "All")
            allQuestions
        else
            allQuestions.filter {

                it.category == selectedCategory
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
                .padding(14.dp)
        ) {

            // HEADER

            Card(

                shape =
                    RoundedCornerShape(28.dp),

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

                        text =

                            if (isKannada)
                                "🤖 ರೈತ AI ಸಹಾಯಕ"
                            else
                                "🤖 Farmer AI Assistant",

                        fontSize = 28.sp,

                        fontWeight =
                            FontWeight.Bold
                    )

                    Spacer(
                        modifier =
                            Modifier.height(6.dp)
                    )

                    Text(

                        text =

                            if (isKannada)
                                "ಬೆಳೆ, ರೋಗ, ಹವಾಮಾನ ಮಾಹಿತಿ"
                            else
                                "Crop, disease & weather guidance",

                        color = Color.Gray
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            // CHAT AREA

            LazyColumn(

                modifier =
                    Modifier.weight(1f)
            ) {

                items(chats) { chat ->

                    Card(

                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp),

                        shape =
                            RoundedCornerShape(22.dp),

                        colors =
                            CardDefaults.cardColors(
                                containerColor =
                                    Color.White
                            )
                    ) {

                        Text(

                            text = chat,

                            modifier =
                                Modifier.padding(18.dp),

                            fontSize = 16.sp
                        )
                    }
                }
            }

            // CATEGORY FILTERS

            LazyRow(

                modifier =
                    Modifier.padding(vertical = 10.dp)
            ) {

                items(categories) { category ->

                    FilterChip(

                        selected =
                            selectedCategory == category,

                        onClick = {

                            selectedCategory = category
                        },

                        label = {

                            Text(category)
                        },

                        modifier =
                            Modifier.padding(end = 10.dp)
                    )
                }
            }

            // QUESTION SELECTOR

            Card(

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
                        Modifier.padding(14.dp)
                ) {

                    Text(

                        text =

                            if (isKannada)
                                "🌾 ಪ್ರಶ್ನೆ ಆಯ್ಕೆ ಮಾಡಿ"
                            else
                                "🌾 Select Question",

                        fontWeight =
                            FontWeight.Bold
                    )

                    Spacer(
                        modifier =
                            Modifier.height(12.dp)
                    )

                    Row(

                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        OutlinedTextField(

                            value = message,

                            onValueChange = {

                                message = it
                            },

                            modifier =
                                Modifier.weight(1f),

                            label = {

                                Text(

                                    if (isKannada)
                                        "ಪ್ರಶ್ನೆ ಕೇಳಿ"
                                    else
                                        "Ask Question"
                                )
                            },

                            shape =
                                RoundedCornerShape(18.dp)
                        )

                        Spacer(
                            modifier =
                                Modifier.width(10.dp)
                        )

                        FloatingActionButton(

                            onClick = {

                                if (message.isNotEmpty()) {

                                    val reply =

                                        if (isKannada)
                                            "🚜 ಬೆಳೆಗಳನ್ನು ನಿಯಮಿತವಾಗಿ ಪರಿಶೀಲಿಸಿ."
                                        else
                                            "🚜 Monitor crops regularly."

                                    chats.add(
                                        "👨‍🌾 $message"
                                    )

                                    chats.add(
                                        "🤖 $reply"
                                    )

                                    message = ""
                                }
                            },

                            containerColor =
                                Color(0xFF2E7D32)
                        ) {

                            Icon(

                                Icons.Default.Send,

                                contentDescription = null,

                                tint = Color.White
                            )
                        }
                    }

                    Spacer(
                        modifier =
                            Modifier.height(14.dp)
                    )

                    // QUESTION CHIPS

                    LazyRow {

                        items(filteredQuestions) { item ->

                            AssistChip(

                                onClick = {

                                    val question =

                                        if (isKannada)
                                            item.kannadaQuestion
                                        else
                                            item.englishQuestion

                                    val answer =

                                        if (isKannada)
                                            item.kannadaAnswer
                                        else
                                            item.englishAnswer

                                    chats.add(
                                        "👨‍🌾 $question"
                                    )

                                    chats.add(
                                        "🤖 $answer"
                                    )
                                },

                                label = {

                                    Text(

                                        text =

                                            if (isKannada)
                                                item.kannadaQuestion
                                            else
                                                item.englishQuestion
                                    )
                                },

                                modifier =
                                    Modifier.padding(
                                        end = 10.dp
                                    )
                            )
                        }
                    }
                }
            }
        }
    }
}