package com.mintsdev.api_task.ui.theme.screens

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

import com.mintsdev.api_task.ui.theme.viewmodel.InitializeScreenViewModel



@Composable
fun InitializeScreen(viewModel: InitializeScreenViewModel) {
    val apiAddress by viewModel.apiAddress.observeAsState(initial = "")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "API Address: $apiAddress")
        Button(onClick = { viewModel.fetchApiAddress() }) {
            Text(text = "Fetch API Address")
        }
    }
}