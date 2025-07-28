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

object MyProfileScreen : Destination {
    override val label = "MyProfileScreen"
    override val route = "MyProfileScreen"
}

object ClassScheduleScreen : Destination {
    override val label = "ClassScheduleScreen"
    override val route = "ClassScheduleScreen"
}

object ClassRequestsScreen : Destination {
    override val label = "ClassRequestsScreen"
    override val route = "ClassRequestsScreen"
}

object ChangePasswordScreen : Destination {
    override val label = "ChangePasswordScreen"
    override val route = "ChangePasswordScreen"
}
