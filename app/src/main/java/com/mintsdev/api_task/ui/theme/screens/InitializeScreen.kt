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

import com.mintsdev.api_task.ui.theme.viewmodel.InitializeScreenViewModel



@Composable
fun InitializeScreen(viewModel: InitializeScreenViewModel) {

    val apiAddress by viewModel.apiAddress.observeAsState(initial = "")
    val connectionStatus by viewModel.connectionStatus.observeAsState(initial = "Press Button to check")

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "API Address: $apiAddress")
        Text(text = "Internet Status: $connectionStatus")
        Button(onClick = { viewModel.fetchApiAddress() }) {
            Text(text = "Fetch API Address")
        }
        Button(onClick = {
                         Toast.makeText(context, "ha-ha, not now", Toast.LENGTH_LONG).show()
        }, enabled = apiAddress.isNotEmpty()) {
            Text(text = "Go to Auth")
        }
    }
}