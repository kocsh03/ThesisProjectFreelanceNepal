package com.example.freelancenepal.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
        private const val BASE_URL = "http://10.0.2.2:3000/"
//private const val BASE_URL = "http://172.25.0.225:3000/"

    //    private const val BASE_URL = "http://192.168.43.32:3000/api/v1/"
//    private const val BASE_URL = "http://192.168.1.24:3000/"
    var token : String? = null
    var id: String? = null

    private val okHttp = OkHttpClient.Builder()

    //Create retrofit builder
    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    //Create retrofit instance
    private val retrofit = retrofitBuilder.build()

    // Load image path in Service Builder class
    fun loadImagePath(): String {
        val arr = BASE_URL.split("/").toTypedArray()
        return arr[0] + "/" + arr[1] + arr[2] + "/uploads/"
    }

    //Generic function
    fun <T> buildService(serviceType : Class<T>): T{
        return retrofit.create(serviceType)
    }
}