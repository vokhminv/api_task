package com.mintsdev.api_task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mintsdev.api_task.datastore.StoreManager
import com.mintsdev.api_task.ui.theme.Api_taskTheme
import com.mintsdev.api_task.ui.theme.screens.CatalogScreen
import com.mintsdev.api_task.ui.theme.screens.InitializeScreen
import com.mintsdev.api_task.ui.theme.screens.LoginScreen
import com.mintsdev.api_task.ui.theme.viewmodel.CatalogViewModel
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
                            navController = navController,
                        )
                    }
                    composable("loginScreen") {
                        LoginScreen(
                            viewModel = LoginViewModel(application),
                            navController = navController
                        )
                    }
                    composable("catalogScreen"){
                        CatalogScreen(
                            viewModel = CatalogViewModel(application),
                            navController = navController
                        )
                    }
                }


            }
        }
    }
}

