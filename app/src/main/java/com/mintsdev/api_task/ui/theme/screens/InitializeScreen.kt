package com.mintsdev.api_task.ui.theme.screens


import android.widget.Toast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

import com.mintsdev.api_task.ui.theme.viewmodel.InitializeScreenViewModel




@Composable
fun InitializeScreen(viewModel: InitializeScreenViewModel, navController: NavController) {

    val apiAddress by viewModel.apiAddress.observeAsState(initial = "not recieved yet")
    val connectionStatus by viewModel.connectionStatus.observeAsState(initial = "Press button to check")

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "API Address: $apiAddress")
        Text(text = "Internet Status: $connectionStatus")
        Button(onClick = { viewModel.fetchApiAddress(context) }) {
            Text(text = "Fetch API Address")
        }
        Button(onClick = {
                         navController.navigate("loginScreen")
        },
            enabled = viewModel.apiAddress.isInitialized) {
            Text(text = "Go to Auth")
        }
    }
}