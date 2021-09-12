package com.example.freelancenepal.api


import com.example.freelancenepal.entity.Gig
import com.example.freelancenepal.response.AddGigResponse
import com.example.freelancenepal.response.DeleteGigResponse
import com.example.freelancenepal.response.GetAllGigResponse
import com.example.freelancenepal.response.ImageResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface GigAPI {
    //Add Pet
    @POST("pet/")
    suspend fun addPet(
        @Header("Authorization") token : String,
        @Body gig : Gig
    ): Response<AddGigResponse>

    //View Pet
    @GET("pet/")
    suspend fun getAllPet(
        @Header("Authorization") token : String,
    ): Response<GetAllGigResponse>

    //Delete Pet
    @DELETE("pet/{id}")
    suspend fun deletePet(
        @Header("Authorization") token : String,
        @Path("id") id : String
    ): Response<DeleteGigResponse>

    //Update Pet Details
    @PUT("pet/{id}/update")
    suspend fun updatePet(
        @Header("Authorization") token : String,
        @Path("id") id : String,
        @Body gig : Gig
    ): Response<DeleteGigResponse>

    //for uploading image or files
    @Multipart
    @PUT("pet/{id}/photo")
    suspend fun uploadImage(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Part file: MultipartBody.Part
    ): Response<ImageResponse>
}