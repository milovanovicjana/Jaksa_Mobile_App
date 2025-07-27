package com.example.jaksaapp.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jaksaapp.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository = UserRepository()) : ViewModel() {
    private var token: String? = null

    fun setToken(token: String?) {
        this.token = token
    }

    fun getLoggedInUser() {
        viewModelScope.launch {
            if (token == null) {
                Log.d("tag", "Token nije postavljen")
                return@launch
            }
            try {
                val response = repository.getLoggedInUser("Bearer $token")
                if (response.isSuccessful) {
                    Log.d("tag", response.body().toString())
                } else {
                    Log.d("tag", "Greska")
                }
            } catch (e: Exception) {
                Log.d("tag", "Izuzetak")
            }
        }
    }
}
