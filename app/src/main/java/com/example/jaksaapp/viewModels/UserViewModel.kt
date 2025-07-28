package com.example.jaksaapp.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jaksaapp.remote.dto.ChangePasswordRequest
import com.example.jaksaapp.remote.dto.UserDto
import com.example.jaksaapp.repository.UserRepository
import com.example.jaksaapp.ui.theme.utils.ValidationRules
import com.example.jaksaapp.ui.theme.utils.validateField
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository = UserRepository()) : ViewModel() {
    private var token: String? = null
    var loggedInUser by mutableStateOf<UserDto?>(null)
        private set
    var updateFieldsResult by mutableStateOf<String?>(null)
        private set

    var changePasswordResult by mutableStateOf<String?>(null)
        private set

    fun setToken(token: String?) {
        this.token = token
    }

    fun getLoggedInUser() {
        viewModelScope.launch {
            try {
                val response = repository.getLoggedInUser("Bearer $token")
                if (response.isSuccessful) {
                    loggedInUser = response.body()
                    Log.d("UserViewModel", "Ulogovani korisnik dohvacen: ${loggedInUser?.username}")
                } else {
                    Log.d("UserViewModel", "Greska u dohvatanju korisnika: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.d("UserViewModel", "Izuzetak: ${e.localizedMessage}")
            }
        }
    }

    fun validateChangePasswordFields(
        oldPassword: String,
        newPassword: String,
        repeatedPassword: String
    ): String {
        if (oldPassword.isBlank() || newPassword.isBlank() || repeatedPassword.isBlank()) {
            return "Polja ne smeju biti prazna!"
        }
        validateField(newPassword, ValidationRules.passwordRegex)?.let {
            return "Nova lozinka mora imati najmanje 6 karaktera, uključujući veliko i malo slovo i jedan broj!!"
        }
        if(newPassword!=repeatedPassword){
            return "Nova i ponovljena lozinka moraju biti iste!"
        }
        return ""
    }

    fun updateUser(user: UserDto) {
        viewModelScope.launch {
            try {
                updateFieldsResult = null
                val response = repository.updateUser("Bearer $token", user)
                if (response.isSuccessful) {
                    updateFieldsResult = "Uspesno azuriranje podataka!"
                    loggedInUser = user
                } else {
                    updateFieldsResult = "Greska: ${response.errorBody()?.string()}"
                }
            } catch (e: Exception) {
                updateFieldsResult = e.localizedMessage
            }
        }
    }

    fun validateUpdateUserFields(
        firstname: String,
        lastname: String,
        phone: String
    ): String {
        if (firstname.isBlank() || lastname.isBlank() || lastname.isBlank()) {
            return "Polja ne smeju biti prazna!"
        }
        validateField(phone, ValidationRules.phoneRegex)?.let {
            return "Broj mora sadržati 6-15 cifara!"
        }
        return ""
    }

    fun changePassword(request: ChangePasswordRequest) {
        viewModelScope.launch {
            try {
                changePasswordResult = null
                val response = repository.changePassword("Bearer $token", request)
                if (response.isSuccessful) {
                    changePasswordResult = "Uspesno sacuvana nova lozinka!"
                } else {
                    changePasswordResult = "Greska: ${response.errorBody()?.string()}"
                }
            } catch (e: Exception) {
                changePasswordResult = e.localizedMessage
            }
        }
    }
}
