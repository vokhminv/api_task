package com.mintsdev.api_task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mintsdev.api_task.ui.theme.Api_taskTheme
import com.mintsdev.api_task.ui.theme.screens.InitializeScreen
import com.mintsdev.api_task.ui.theme.screens.LoginScreen
import com.mintsdev.api_task.ui.theme.viewmodel.InitializeScreenViewModel
import com.mintsdev.api_task.ui.theme.viewmodel.LoginViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            Api_taskTheme {
                NavHost(navController = navController, startDestination = "initializeScreen") {
                    composable("initializeScreen") {
                        InitializeScreen(
                            viewModel = InitializeScreenViewModel(),
                            navController = navController
                        )
                    }
                    composable("loginScreen") {
                        LoginScreen(viewModel = LoginViewModel(), navController = navController)
                    }
                }


            }
        }
    }
}

