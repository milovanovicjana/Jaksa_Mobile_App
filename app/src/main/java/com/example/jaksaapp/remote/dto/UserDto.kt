package com.example.jaksaapp.remote.dto

data class UserDto(
    val firstname: String,
    val lastname: String,
    val email: String,
    val phone: String,
    val username: String,
    val role: Role? = null
)
