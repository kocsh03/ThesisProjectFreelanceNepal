package com.example.freelancenepal.api

import com.example.freelancenepal.entity.Favourite
import com.example.freelancenepal.response.*
import retrofit2.Response
import retrofit2.http.*

interface FavouriteAPI {
    @POST("/favourite/add")
    suspend fun addItemToFavourite(
        @Header("Authorization") token: String,
        @Body favourite: Favourite
    ): Response<AddFavouriteResponse>

    @GET("/favourite/")
    suspend fun getFavouriteItems(
        @Header("Authorization") token: String,
    ): Response<GetFavouriteItemsResponse>

    @DELETE("/favourite/delete/{id}")
    suspend fun deleteFavouriteItem(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<DeleteFavouriteResponse>
}