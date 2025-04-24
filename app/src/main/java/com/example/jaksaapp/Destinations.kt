package com.example.jaksaapp

interface Destination {
    val label: String
    val route: String
}

object HomeScreen : Destination {
    override val label = "HomeScreen"
    override val route = "HomeScreen"
}

object AboutMeScreen : Destination {
    override val label = "AboutMeScreen"
    override val route = "AboutMeScreen"
}

object LogInScreen : Destination {
    override val label = "LogInScreen"
    override val route = "LogInScreen"
}

object RegistrationScreen : Destination {
    override val label = "RegistrationScreen"
    override val route = "RegistrationScreen"
}

object ContactScreen : Destination {
    override val label = "ContactScreen"
    override val route = "ContactScreen"
}
