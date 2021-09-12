package com.example.freelancenepal.response

import com.example.freelancenepal.entity.Gig


class GetAllGigResponse (
    val success : Boolean? = null,
    val count: Int? = null,
    val data : MutableList<Gig>? = null
)