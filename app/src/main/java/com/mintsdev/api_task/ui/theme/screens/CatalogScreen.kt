package com.mintsdev.api_task.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mintsdev.api_task.ui.theme.viewmodel.CatalogViewModel


@Composable
fun CatalogScreen(viewModel: CatalogViewModel, navController: NavController){
    var storedToken by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit){
        storedToken = viewModel.getStoredToken().toString()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (storedToken != null) {
            Text("Stored Token: $storedToken")
        } else
            Text("oops, where is token?")
    }
}