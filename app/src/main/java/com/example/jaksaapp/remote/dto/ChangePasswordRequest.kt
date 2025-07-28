package com.example.jaksaapp.remote.dto

data class ChangePasswordRequest(
    val currentPassword: String,
    val newPassword: String
)
