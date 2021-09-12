package com.example.freelancenepal.repository

import com.example.freelancenepal.api.FavouriteAPI
import com.example.freelancenepal.api.MyApiRequest
import com.example.freelancenepal.api.ServiceBuilder
import com.example.freelancenepal.entity.Favourite
import com.example.freelancenepal.response.*

class FavouriteRepo:MyApiRequest() {
    private val FavouriteAPI= ServiceBuilder.buildService(FavouriteAPI::class.java)

    suspend fun addItemToFavourite(favourite: Favourite): AddFavouriteResponse {
       return apiRequest {
           FavouriteAPI.addItemToFavourite(
               ServiceBuilder.token!!, favourite
           )
       }
       }

    suspend fun getFavouriteItems(): GetFavouriteItemsResponse {
        return apiRequest {
            FavouriteAPI.getFavouriteItems(ServiceBuilder.token!!)
        }
    }

    suspend fun  deleteCartItem(id: String): DeleteFavouriteResponse {
        return apiRequest {
            FavouriteAPI.deleteFavouriteItem(ServiceBuilder.token!!,id)
        }
    }
}