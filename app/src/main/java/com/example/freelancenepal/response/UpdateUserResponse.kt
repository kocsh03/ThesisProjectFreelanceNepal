package com.example.freelancenepal.response

import com.example.freelancenepal.entity.Freelancer

data class UpdateUserResponse(
    val success: Boolean? = null,
    val data: Freelancer? = null
)