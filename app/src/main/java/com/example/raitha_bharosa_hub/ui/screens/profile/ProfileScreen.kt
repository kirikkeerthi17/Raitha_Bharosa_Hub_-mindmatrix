package com.example.raitha_bharosa_hub.ui.screens.profile

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.raitha_bharosa_hub.data.local.UserEntity

@Composable
fun ProfileScreen(user: UserEntity, onBack: () -> Unit) {

    Column(Modifier.padding(20.dp)) {

        Text("Profile")

        Text("Name: ${user.name}")
        Text("Crop: ${user.crop}")
        Text("Language: ${user.language}")

        Spacer(Modifier.height(20.dp))

        Button(onClick = onBack) {
            Text("Back")
        }
    }
}