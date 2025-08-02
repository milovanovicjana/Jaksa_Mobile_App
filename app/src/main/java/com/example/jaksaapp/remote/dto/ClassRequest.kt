package com.example.jaksaapp.remote.dto

data class ClassRequest(
    val date: String,
    val timeStart: String,
    val duration: String,
    val description: String,
    val studentId: Long
)
