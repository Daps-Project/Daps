package com.daps.app.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ServiceBuilder {
    private val gson: Gson = GsonBuilder()
        .setLenient()
        .create()
    private val client: OkHttpClient = OkHttpClient.Builder().build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:5000")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()

    fun<T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}