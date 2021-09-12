package com.example.freelancenepal.response

import com.example.freelancenepal.entity.Favourite

class GetFavouriteItemsResponse (
    val success: Boolean? = null,
    val count: Int? =null,
    val data: MutableList<Favourite>? = null
)