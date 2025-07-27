package com.example.jaksaapp.remote

import com.example.jaksaapp.remote.dto.UserDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface UserService {
    @GET("/api/user/me")
    suspend fun getLoggedInUser(@Header("Authorization") token: String): Response<UserDto>
}
