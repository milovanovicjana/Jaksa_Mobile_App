package com.example.jaksaapp

import AboutMeScreen
import ClassRequestsScreen
import ClassScheduleScreen
import ContactScreen
import HomeScreen
import LogInScreen
import MyProfileScreen
import RegistrationScreen
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.jaksaapp.ui.theme.JaksaAppTheme
import com.example.jaksaapp.ui.theme.screens.ChangePasswordScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JaksaAppTheme {
                val navController = rememberAnimatedNavController()
                val startRoute by remember { mutableStateOf(LogInScreen.route) }
                val isLoggedIn = remember { mutableStateOf(false) }

                AnimatedNavHost(
                    navController = navController,
                    startDestination = startRoute
                ) {
                    composable(route = HomeScreen.route) {
                        HomeScreen(isLoggedIn.value, navController)
                    }
                    composable(route = AboutMeScreen.route) {
                        AboutMeScreen(isLoggedIn.value, navController)
                    }
                    composable(route = LogInScreen.route) {
                        LaunchedEffect(Unit) {
                            isLoggedIn.value = false
                        }
                        LogInScreen(isLoggedIn.value, navController) { isLoggedIn.value = true }
                    }
                    composable(route = RegistrationScreen.route) {
                        RegistrationScreen(isLoggedIn.value, navController)
                    }
                    composable(route = ContactScreen.route) {
                        ContactScreen(isLoggedIn.value, navController)
                    }
                    composable(route = MyProfileScreen.route) {
                        MyProfileScreen(isLoggedIn.value, navController)
                    }
                    composable(route = ClassRequestsScreen.route) {
                        ClassRequestsScreen(isLoggedIn.value, navController)
                    }
                    composable(route = ClassScheduleScreen.route) {
                        ClassScheduleScreen(isLoggedIn.value, navController)
                    }
                    composable(route = ChangePasswordScreen.route) {
                        ChangePasswordScreen(isLoggedIn.value, navController)
                    }
                }
            }
        }
    }
}
