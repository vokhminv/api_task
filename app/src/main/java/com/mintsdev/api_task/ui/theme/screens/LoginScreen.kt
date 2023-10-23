package com.mintsdev.api_task.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mintsdev.api_task.ui.theme.viewmodel.LoginViewModel



@Composable
fun LoginScreen(viewModel: LoginViewModel, navController: NavController) {
    val token by viewModel.token.observeAsState(initial = "")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "token: $token")
        Button(
            onClick = {
                viewModel.authenticate()
            }
        ) {
            Text("send consts to get token")
        }
        Button(
            onClick = {
                navController.navigate("catalogScreen")
            },
            enabled = viewModel.token.isInitialized
        ) {
            Text("go next to catalog")
        }
    }
}