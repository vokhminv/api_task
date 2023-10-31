package com.mintsdev.api_task.api


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClientInit {
    val apiServiceInit: ApiServiceInit by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://cr-test-ribu2uaqea-ey.a.run.app")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiServiceInit::class.java)
    }
}

object ApiClientMain {
    val apiServiceMain: ApiServiceMain by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://ca-test-ribu2uaqea-ey.a.run.app")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiServiceMain::class.java)
    }
}