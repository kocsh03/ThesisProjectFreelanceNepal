package com.example.freelancenepal.repository

import com.example.freelancenepal.api.MyApiRequest
import com.example.freelancenepal.api.GigAPI

import com.example.freelancenepal.api.ServiceBuilder
import com.example.freelancenepal.entity.Gig
import com.example.freelancenepal.response.AddGigResponse
import com.example.freelancenepal.response.DeleteGigResponse
import com.example.freelancenepal.response.GetAllGigResponse


import com.example.freelancenepal.response.ImageResponse
import okhttp3.MultipartBody

class GigRepository: MyApiRequest() {
    private  val petAPI =
        ServiceBuilder.buildService(GigAPI::class.java)

    //Add Pet
    suspend fun addPet(gig : Gig): AddGigResponse {
        return apiRequest {
            petAPI.addPet(
                ServiceBuilder.token!!,gig
            )
        }
    }
    //View Pets
    suspend fun getAllPet(): GetAllGigResponse {
        return apiRequest {
            petAPI.getAllPet(
                ServiceBuilder.token!!
            )
        }
    }
    //Delete Pet
    suspend fun deletePet(id :String): DeleteGigResponse {
        return apiRequest {
            petAPI.deletePet(
                ServiceBuilder.token!!,id
            )
        }
    }
    //Update Pet
    suspend fun updatePet(id :String, gig: Gig): DeleteGigResponse {
        return apiRequest {
            petAPI.updatePet(
                ServiceBuilder.token!!, id, gig
            )
        }
    }
    //upload image
    suspend fun uploadImage(id: String, body: MultipartBody.Part)
            : ImageResponse {
        return apiRequest {
            petAPI.uploadImage(
                ServiceBuilder.token!!, id, body
            )
        }
    }
}