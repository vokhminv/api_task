package com.mintsdev.api_task.api

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("/routes/")
    suspend fun getApiAddress(
        @Query("appName") appName: String,
        @Query("v") v: Int
    ) : Response<RouteResponse>

    @FormUrlEncoded
    @POST("{api_address}/users/login/")
    suspend fun getToken(
        @Field("login") login: String,
        @Field("password") password: String,
        @Field("devman") devman: String,
        @Field("devmod") devmod: String,
        @Field("devavs") devavs: String,
        @Field("devaid") devaid: String
    ): Response<TokenResponse>
}

data class RouteResponse(
    @SerializedName("route")
    val route: String
)

data class TokenResponse(
    @SerializedName("token")
    val token: String
)