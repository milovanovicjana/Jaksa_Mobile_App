package com.example.jaksaapp.remote

import com.example.jaksaapp.remote.dto.ChangePasswordRequest
import com.example.jaksaapp.remote.dto.UserDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT

interface UserService {
    @GET("/api/user/me")
    suspend fun getLoggedInUser(@Header("Authorization") token: String): Response<UserDto>

    @PUT("/api/user/me")
    suspend fun updateUser(@Header("Authorization") token: String, @Body user: UserDto): Response<ResponseBody>

    @PUT("/api/user/change-password")
    suspend fun changePassword(@Header("Authorization") token: String, @Body request: ChangePasswordRequest): Response<ResponseBody>

    @GET("/api/user/allStudents")
    suspend fun getAllStudents(@Header("Authorization") token: String): Response<List<UserDto>>
}
