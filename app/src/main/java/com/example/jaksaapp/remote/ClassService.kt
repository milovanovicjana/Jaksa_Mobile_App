package com.example.jaksaapp.remote

import com.example.jaksaapp.remote.dto.ClassDto
import com.example.jaksaapp.remote.dto.ClassRequest
import com.example.jaksaapp.remote.dto.ClassesByMonthRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ClassService {

    @POST("/api/class/classesByMonth")
    suspend fun getClassesByMonth(@Header("Authorization") token: String, @Body request: ClassesByMonthRequest): Response<List<ClassDto>>

    @POST("/api/class/createClassRequest")
    suspend fun createClassRequest(@Header("Authorization") token: String, @Body request: ClassRequest): Response<String>

    @GET("api/class/allClassesForStudent/{studentId}")
    suspend fun getAllClassesForStudent(@Header("Authorization") token: String, @Path("studentId") studentId: Long):
        Response<List<ClassDto>>

    @GET("api/class/allClasses")
    suspend fun getAllClasses(@Header("Authorization") token: String): Response<List<ClassDto>>

    @PUT("api/class/acceptRequest/{classId}")
    suspend fun acceptRequest(@Header("Authorization") token: String, @Path("classId") classId: Long): Response<String>

    @PUT("api/class/rejectRequest/{classId}")
    suspend fun rejectRequest(@Header("Authorization") token: String, @Path("classId") classId: Long): Response<String>
}
