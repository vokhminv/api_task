package com.mintsdev.api_task.api

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiServiceInit {
    @GET("/routes/")
    suspend fun getApiAddress(
        @Query("appName") appName: String,
        @Query("v") v: Int
    ) : Response<RouteResponse>
}

interface ApiServiceMain {
    @FormUrlEncoded
    @POST("/users/login/")
    suspend fun getToken(
        @Field("login") login: String,
        @Field("password") password: String,
        @Field("devman") devman: String,
        @Field("devmod") devmod: String,
        @Field("devavs") devavs: String,
        @Field("devaid") devaid: String
    ): Response<TokenResponse>


    @GET("/catalog/brands/")
    suspend fun getBrands(
        @Query("token") token: String
    ): Response<Brands>
}


data class RouteResponse(
    val route: String
)

data class TokenResponse(
    val token: String
)


data class Brands(
    val brands: Map<String,BrandData>
)

data class BrandData(
    val brandId: Int,
    val brandName: String,
    val brandImage: String
)
