package com.example.freelancenepal.repository

import com.example.freelancenepal.api.MyApiRequest
import com.example.freelancenepal.api.ServiceBuilder
import com.example.freelancenepal.entity.Client
import com.example.freelancenepal.response.LoginResponse

class ClientRepository: MyApiRequest() {
    private val AdminAPI = ServiceBuilder.buildService(com.example.freelancenepal.api.ClientAPI::class.java)

    //Register Admin
    suspend fun registerAdmin(client : Client) : LoginResponse {
        return apiRequest {
            AdminAPI.registerAdmin(client)
        }
    }
    suspend fun loginAdmin(username : String, password : String): LoginResponse {
        return apiRequest {
            AdminAPI.checkAdmin(username, password)
        }
    }
}