package com.example.jaksaapp.repository

import com.example.jaksaapp.remote.ApiClient
import com.example.jaksaapp.remote.dto.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body

class AuthRepository {
    suspend fun registerUser(@Body request: RegisterRequest): Response<String> {
        return ApiClient.authService.registerUser(request)
    }
}
