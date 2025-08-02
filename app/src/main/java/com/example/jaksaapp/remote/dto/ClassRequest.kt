package com.example.jaksaapp.remote.dto

data class ClassRequest(
    val studentId: Long,
    val date: String,
    val timeStart: String,
    val duration: String,
    val description: String,
    var requestedByStudent: Boolean
)
