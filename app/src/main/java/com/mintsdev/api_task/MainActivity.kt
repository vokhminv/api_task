package com.mintsdev.api_task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mintsdev.api_task.ui.theme.Api_taskTheme
import com.mintsdev.api_task.ui.theme.screens.InitializeScreen
import com.mintsdev.api_task.ui.theme.viewmodel.InitializeScreenViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Api_taskTheme {
                InitializeScreen(viewModel = InitializeScreenViewModel())

            }
        }
    }
}

