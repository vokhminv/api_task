package com.mintsdev.api_task.api


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val apiService: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://cr-test-ribu2uaqea-ey.a.run.app")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
}