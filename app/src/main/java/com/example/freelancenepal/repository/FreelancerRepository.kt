package com.example.freelancenepal.repository

import com.example.freelancenepal.api.MyApiRequest
import com.example.freelancenepal.api.ServiceBuilder
import com.example.freelancenepal.entity.Freelancer
import com.example.freelancenepal.response.GetUserProfileResponse
import com.example.freelancenepal.response.LoginResponse
import com.example.freelancenepal.response.UpdateUserResponse

class FreelancerRepository: MyApiRequest() {
    private val UserAPI = ServiceBuilder.buildService(com.example.freelancenepal.api.FreelancerAPI::class.java)

    //Register User
    suspend fun registerUser(freelancer : Freelancer) : LoginResponse {
        return apiRequest {
            UserAPI.registerUser(freelancer)
        }
    }

    //login user
    suspend fun loginUser(username : String, password : String): LoginResponse {
        return apiRequest {
            UserAPI.checkUser(username, password)
        }
    }
  //Get Profile details
    suspend fun getMe(): GetUserProfileResponse {
        return apiRequest {
            UserAPI.getMe(ServiceBuilder.token!!)
        }
    }
   //Update user profile
    suspend fun updateUser(id:String, freelancer: Freelancer): UpdateUserResponse {
        return apiRequest {
            UserAPI.updateUser(id, freelancer)
        }
    }
}