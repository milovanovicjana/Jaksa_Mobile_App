package com.example.jaksaapp.repository

import com.example.jaksaapp.remote.ApiClient
import com.example.jaksaapp.remote.dto.UserDto
import retrofit2.Response
import retrofit2.http.Header

class UserRepository {
    suspend fun getLoggedInUser(@Header("Authorization") token: String): Response<UserDto> {
        return ApiClient.userService.getLoggedInUser(token)
    }
}
