package com.example.jaksaapp.remote

import com.example.jaksaapp.remote.dto.ClassDto
import com.example.jaksaapp.remote.dto.ClassRequest
import com.example.jaksaapp.remote.dto.ClassesByMonthRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ClassService {

    @POST("/api/class/classesByMonth")
    suspend fun getClassesByMonth(@Header("Authorization") token: String, @Body request: ClassesByMonthRequest): Response<List<ClassDto>>

    @POST("/api/class/createClassRequest")
    suspend fun createClassRequest(@Header("Authorization") token: String, @Body request: ClassRequest): Response<String>
}
