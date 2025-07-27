package com.example.jaksaapp.remote

import com.example.jaksaapp.remote.dto.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/api/register")
    suspend fun registerUser(@Body request: RegisterRequest): Response<String>
}
