package com.example.jaksaapp.ui.theme.utils

object ValidationRules {
    val passwordRegex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}$")

    val emailRegex = Regex("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")

    val phoneRegex = Regex("^[0-9]{6,15}$")
}

fun validateField(value: String, regex: Regex): String? {
    return if (!regex.matches(value)) "error" else null
}
