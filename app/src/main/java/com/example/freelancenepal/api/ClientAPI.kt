package com.example.freelancenepal.api

import com.example.freelancenepal.entity.Client
import com.example.freelancenepal.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ClientAPI {
    //register Admin
    @POST("admin/register")
    suspend fun registerAdmin(
        @Body client : Client
    ): Response<LoginResponse>

    //login Admin
    @FormUrlEncoded
    @POST("admin/login")
    suspend fun checkAdmin(
        @Field("username") username : String,
        @Field("password") password : String
    ): Response<LoginResponse>
}