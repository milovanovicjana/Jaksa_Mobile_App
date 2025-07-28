package com.example.jaksaapp.repository

import com.example.jaksaapp.remote.ApiClient
import com.example.jaksaapp.remote.dto.ChangePasswordRequest
import com.example.jaksaapp.remote.dto.UserDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header

class UserRepository {
    suspend fun getLoggedInUser(@Header("Authorization") token: String): Response<UserDto> {
        return ApiClient.userService.getLoggedInUser(token)
    }

    suspend fun updateUser(@Header("Authorization") token: String, @Body user: UserDto): Response<ResponseBody> {
        return ApiClient.userService.updateUser(token, user)
    }

    suspend fun changePassword(@Header("Authorization") token: String, @Body request: ChangePasswordRequest): Response<ResponseBody> {
        return ApiClient.userService.changePassword(token, request)
    }
}
