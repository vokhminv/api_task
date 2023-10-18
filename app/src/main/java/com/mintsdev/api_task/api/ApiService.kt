package com.mintsdev.api_task.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/routes/")
    suspend fun getApiAddress(
        @Query("appName") appName: String,
        @Query("v") v: Int
    ) : Response<RouteResponse>
}

data class RouteResponse(
    val route: String
)

