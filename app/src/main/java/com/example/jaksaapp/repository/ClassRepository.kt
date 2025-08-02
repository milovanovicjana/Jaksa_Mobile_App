package com.example.jaksaapp.repository

import com.example.jaksaapp.remote.ApiClient
import com.example.jaksaapp.remote.dto.ClassDto
import com.example.jaksaapp.remote.dto.ClassRequest
import com.example.jaksaapp.remote.dto.ClassesByMonthRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header

class ClassRepository {
    suspend fun getClassesByMonth(@Header("Authorization") token: String, @Body request: ClassesByMonthRequest): Response<List<ClassDto>> {
        return ApiClient.classService.getClassesByMonth(token, request)
    }

    suspend fun createClassRequest(@Header("Authorization") token: String, @Body request: ClassRequest): Response<String> {
        return ApiClient.classService.createClassRequest(token, request)
    }
}
