package com.example.jaksaapp

import AboutMeScreen
import ContactScreen
import HomeScreen
import LogInScreen
import RegistrationScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.jaksaapp.ui.theme.JaksaAppTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JaksaAppTheme {
                val navController = rememberAnimatedNavController()
                val startRoute by remember { mutableStateOf(ContactScreen.route) }
                val isLoggedIn = false

                AnimatedNavHost(
                    navController = navController,
                    startDestination = startRoute
                ) {
                    composable(route = HomeScreen.route) {
                        HomeScreen(isLoggedIn, navController)
                    }
                    composable(route = AboutMeScreen.route) {
                        AboutMeScreen(isLoggedIn, navController)
                    }
                    composable(route = LogInScreen.route) {
                        LogInScreen(isLoggedIn, navController)
                    }
                    composable(route = RegistrationScreen.route) {
                        RegistrationScreen(isLoggedIn, navController)
                    }
                    composable(route = ContactScreen.route) {
                        ContactScreen(isLoggedIn, navController)
                    }
                }
            }
        }
    }
}
