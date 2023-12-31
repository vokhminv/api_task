package com.mintsdev.api_task.ui.theme.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.mintsdev.api_task.api.BrandData
import com.mintsdev.api_task.ui.theme.viewmodel.CatalogViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(viewModel: CatalogViewModel, navController: NavController) {
    val brandsData: State<Map<String, BrandData>?> = viewModel.brandsData.observeAsState()


    LaunchedEffect(Unit) {
        viewModel.loadCatalog()
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("loginScreen")
                    }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "ArrowBack"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    }
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    }
                },
                title = {
                    Text(
                        fontSize = 16.sp,
                        text = "Catalog"
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Row(
            modifier = Modifier
                .padding(innerPadding),
            horizontalArrangement = Arrangement.Center,
        ) {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                columns = GridCells.Adaptive(minSize = 128.dp)
            ) {
                brandsData.value?.let { brands ->
                    Log.d("CatalogScreen", "Received ${brands.size} brands")
                    val brandKeys = brands.keys.toList()
                    items(brandKeys) { brandKey ->
                        val brand = brands[brandKey]
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentSize(Alignment.Center)
                            ) {
                                if (brand != null) {
                                    AsyncImage(
                                        model = brand.brandImage,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .size(50.dp)
                                            .clip(shape = RoundedCornerShape(16.dp)),
                                        contentScale = ContentScale.Fit
                                    )
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                if (brand != null) {
                                    Text(
                                        text = brand.brandName,
                                        fontSize = 18.sp,
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally)
                                    )
                                }

                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}