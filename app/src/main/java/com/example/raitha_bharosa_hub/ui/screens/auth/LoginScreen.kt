package com.example.raitha_bharosa_hub.ui.screens.auth

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.raitha_bharosa_hub.utils.LocaleHelper
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(

    onLoginSuccess: () -> Unit
) {

    val auth = FirebaseAuth.getInstance()

    val context = LocalContext.current

    // LANGUAGE SAVE

    var isKannada by remember {

        mutableStateOf(
            LocaleHelper.getLanguage(context) == "kn"
        )
    }

    // STRINGS

    val appTitle =
        if (isKannada)
            "🌾 ರೈತ ಭರೋಸಾ ಹಬ್"
        else
            "🌾 Raitha Bharosa Hub"

    val subtitle =
        if (isKannada)
            "ಸ್ಮಾರ್ಟ್ ಕೃಷಿ ಸಹಾಯಕ"
        else
            "Smart farming assistant"

    val emailText =
        if (isKannada)
            "ಇಮೇಲ್"
        else
            "Email"

    val passwordText =
        if (isKannada)
            "ಪಾಸ್ವರ್ಡ್"
        else
            "Password"

    val loginText =
        if (isKannada)
            "ಲಾಗಿನ್"
        else
            "Login"

    val createText =
        if (isKannada)
            "ಖಾತೆ ರಚಿಸಿ"
        else
            "Create Account"

    val noAccText =
        if (isKannada)
            "ಖಾತೆ ಇಲ್ಲವೇ?"
        else
            "Don't have an account?"

    val alreadyText =
        if (isKannada)
            "ಈಗಾಗಲೇ ಖಾತೆ ಇದೆಯೇ?"
        else
            "Already registered?"

    val continueLogin =
        if (isKannada)
            "ಲಾಗಿನ್"
        else
            "Login"

    val continueCreate =
        if (isKannada)
            "ರಚಿಸಿ"
        else
            "Create"

    // STATES

    var email by remember {

        mutableStateOf("")
    }

    var password by remember {

        mutableStateOf("")
    }

    var msg by remember {

        mutableStateOf("")
    }

    var isLogin by remember {

        mutableStateOf(true)
    }

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

        // LANGUAGE BUTTON

        IconButton(

            onClick = {

                isKannada = !isKannada

                LocaleHelper.setLocale(

                    context as Activity,

                    if (isKannada) "kn" else "en"
                )
            },

            modifier =
                Modifier
                    .align(Alignment.TopEnd)
                    .padding(20.dp)
        ) {

            Icon(

                Icons.Default.Language,

                contentDescription = null,

                tint = Color(0xFF1B5E20)
            )
        }

        Card(

            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .align(Alignment.Center),

            shape = RoundedCornerShape(32.dp),

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
                    Modifier.padding(26.dp),

                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {

                Text(

                    text = appTitle,

                    fontSize = 30.sp,

                    fontWeight =
                        FontWeight.Bold,

                    color = Color(0xFF1B5E20)
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(

                    text = subtitle,

                    color = Color.Gray
                )

                Spacer(
                    modifier = Modifier.height(34.dp)
                )

                // EMAIL

                OutlinedTextField(

                    value = email,

                    onValueChange = {

                        email = it
                    },

                    label = {

                        Text(emailText)
                    },

                    leadingIcon = {

                        Icon(
                            Icons.Default.Mail,
                            contentDescription = null
                        )
                    },

                    shape =
                        RoundedCornerShape(18.dp),

                    modifier =
                        Modifier.fillMaxWidth()
                )

                Spacer(
                    modifier = Modifier.height(18.dp)
                )

                // PASSWORD

                OutlinedTextField(

                    value = password,

                    onValueChange = {

                        password = it
                    },

                    label = {

                        Text(passwordText)
                    },

                    leadingIcon = {

                        Icon(
                            Icons.Default.Lock,
                            contentDescription = null
                        )
                    },

                    visualTransformation =
                        PasswordVisualTransformation(),

                    shape =
                        RoundedCornerShape(18.dp),

                    modifier =
                        Modifier.fillMaxWidth()
                )

                Spacer(
                    modifier = Modifier.height(28.dp)
                )

                // BUTTON

                Button(

                    onClick = {

                        msg = ""

                        if (isLogin) {

                            auth.signInWithEmailAndPassword(
                                email,
                                password
                            ).addOnCompleteListener {

                                if (it.isSuccessful) {

                                    onLoginSuccess()

                                } else {

                                    msg =

                                        if (isKannada)
                                            "ತಪ್ಪು ಇಮೇಲ್ ಅಥವಾ ಪಾಸ್ವರ್ಡ್"
                                        else
                                            "Wrong email or password"
                                }
                            }

                        } else {

                            auth.createUserWithEmailAndPassword(
                                email,
                                password
                            ).addOnCompleteListener {

                                if (it.isSuccessful) {

                                    onLoginSuccess()

                                } else {

                                    msg =

                                        if (isKannada)
                                            "ಖಾತೆ ಈಗಾಗಲೇ ಇದೆ"
                                        else
                                            "Account already exists"
                                }
                            }
                        }
                    },

                    modifier = Modifier
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

                        text =
                            if (isLogin)
                                loginText
                            else
                                createText,

                        fontSize = 18.sp
                    )
                }

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                // SWITCH MODE

                Row {

                    Text(

                        text =
                            if (isLogin)
                                noAccText
                            else
                                alreadyText
                    )

                    Spacer(
                        modifier = Modifier.width(6.dp)
                    )

                    Text(

                        text =
                            if (isLogin)
                                continueCreate
                            else
                                continueLogin,

                        color =
                            Color(0xFF2E7D32),

                        fontWeight =
                            FontWeight.Bold,

                        modifier =
                            Modifier.clickable {

                                isLogin =
                                    !isLogin

                                msg = ""
                            }
                    )
                }

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                Text(

                    text = msg,

                    color = Color.Red
                )
            }
        }
    }
}