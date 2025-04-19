package com.example.jaksaapp

import HomeScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.jaksaapp.ui.theme.JaksaAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JaksaAppTheme {
                val isLoggedIn = false
                HomeScreen(isLoggedIn)
            }
        }
    }
}
