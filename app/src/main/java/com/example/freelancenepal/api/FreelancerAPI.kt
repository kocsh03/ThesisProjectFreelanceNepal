package com.example.freelancenepal.api

import com.example.freelancenepal.entity.Freelancer
import com.example.freelancenepal.response.GetUserProfileResponse
import com.example.freelancenepal.response.LoginResponse
import com.example.freelancenepal.response.UpdateUserResponse
import retrofit2.Response
import retrofit2.http.*

interface FreelancerAPI {
    //register user
    @POST("/auth/register")
    suspend fun registerUser(
        @Body freelancer : Freelancer
    ): Response<LoginResponse>

    //login user
    @FormUrlEncoded
    @POST("auth/login")
    suspend fun checkUser(
        @Field("username") username : String,
        @Field("password") password : String
    ): Response<LoginResponse>

    //Get user details
    @GET("/auth/me")
    suspend fun getMe(
        @Header("Authorization") token: String,
    ): Response<GetUserProfileResponse>


    //Update user details
    @PUT("/auth/update/user/{id}")
    suspend fun updateUser(
        @Path("id") id: String,
        @Body freelancer: Freelancer
    ): Response<UpdateUserResponse>
}