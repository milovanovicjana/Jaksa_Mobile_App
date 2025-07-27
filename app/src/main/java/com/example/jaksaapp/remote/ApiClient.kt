package com.example.jaksaapp.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object ApiClient {
    private const val BASE_URL = "http://10.0.2.2:8080"

    val authService: AuthService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create()) // Enables receiving plain string responses
            .addConverterFactory(GsonConverterFactory.create()) // Enables receiving JSON responses
            .build()
            .create(AuthService::class.java)
    }
}
