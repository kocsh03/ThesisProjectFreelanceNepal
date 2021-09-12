package com.example.freelancenepal.response


import com.example.freelancenepal.entity.Favourite


data class AddFavouriteResponse (
    val success: Boolean? = null,
    val data: Favourite? = null
)