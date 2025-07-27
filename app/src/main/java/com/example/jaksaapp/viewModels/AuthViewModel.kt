package com.example.jaksaapp.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jaksaapp.remote.dto.LoginRequest
import com.example.jaksaapp.remote.dto.RegisterRequest
import com.example.jaksaapp.repository.AuthRepository
import com.example.jaksaapp.ui.theme.utils.ValidationRules
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository = AuthRepository()) : ViewModel() {
    var registrationResult by mutableStateOf<String?>(null)
        private set

    var loginResult by mutableStateOf<String?>(null)
        private set

    var authToken by mutableStateOf<String?>(null)

    private fun validateField(value: String, regex: Regex): String? {
        return if (!regex.matches(value)) "error" else null
    }
    fun validateRegistrationFields(
        firstname: String,
        lastname: String,
        email: String,
        phone: String,
        username: String,
        password: String
    ): String {
        if (firstname.isBlank() || lastname.isBlank() || username.isBlank() || email.isBlank() || phone.isBlank() || password.isBlank()) {
            return "Sva polja su obavezna!"
        }

        validateField(password, ValidationRules.passwordRegex)?.let {
            return "Lozinka mora imati najmanje 6 karaktera, uključujući veliko i malo slovo i jedan broj!"
        }
        validateField(phone, ValidationRules.phoneRegex)?.let {
            return "Broj mora sadržati 6-15 cifara!"
        }
        validateField(email, ValidationRules.emailRegex)?.let {
            return "Neispravna email adresa!"
        }

        return ""
    }

    fun validateLoginFields(
        username: String,
        password: String
    ): String {
        if (username.isBlank() || password.isBlank()) {
            return "Sva polja su obavezna!"
        }
        return ""
    }

    fun registerUser(request: RegisterRequest) {
        viewModelScope.launch {
            try {
                val response = repository.registerUser(request)
                if (response.isSuccessful) {
                    registrationResult = "Uspesna registracija!"
                } else {
                    registrationResult = "Greska: ${response.errorBody()?.string()}"
                }
            } catch (e: Exception) {
                registrationResult = e.localizedMessage
            }
        }
    }

    fun loginUser(request: LoginRequest) {
        viewModelScope.launch {
            try {
                loginResult = null // reset
                val response = repository.loginUser(request)
                if (response.isSuccessful) {
                    loginResult = "Uspesno logovanje!"
                    authToken = response.body()?.token
                } else {
                    loginResult = "Greska: ${response.errorBody()?.string()}"
                }
            } catch (e: Exception) {
                loginResult = e.localizedMessage
            }
        }
    }
}
