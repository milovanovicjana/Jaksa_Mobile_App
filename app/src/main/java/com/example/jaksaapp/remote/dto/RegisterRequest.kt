package com.example.jaksaapp.remote.dto

data class RegisterRequest(
    val firstname: String,
    val lastname: String,
    val email: String,
    val phone: String,
    val username: String,
    val password: String,
    val role: Role = Role.STUDENT
)
