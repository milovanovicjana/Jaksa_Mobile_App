import androidx.compose.foundation.background
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.jaksaapp.AboutMeScreen
import com.example.jaksaapp.ContactScreen
import com.example.jaksaapp.Destination
import com.example.jaksaapp.HomeScreen
import com.example.jaksaapp.LogInScreen
import com.example.jaksaapp.R
import com.example.jaksaapp.RegistrationScreen
import com.example.jaksaapp.ui.theme.Cream
@Composable
fun DropdownMenuTopBar(expanded: MutableState<Boolean>, isLoggedIn: Boolean, navHostController: NavHostController) {
    val context = LocalContext.current

    val currentBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route ?: HomeScreen.route

    var dropdownMenuItems: List<String>
    var dropdownItemDestinations: List<Destination>
    if (isLoggedIn) {
        val myProfileButtonText = context.getString(R.string.my_profile)
        val logOutButtonText = context.getString(R.string.logout)
        dropdownMenuItems = listOf(myProfileButtonText, logOutButtonText)
        dropdownItemDestinations = listOf(
            AboutMeScreen,
            AboutMeScreen
        )
    } else {
        val homePageButtonText = context.getString(R.string.home_page)
        val aboutMeButtonText = context.getString(R.string.about_me)
        val contactButtonText = context.getString(R.string.contact)

        val loginButtonText = context.getString(R.string.login)
        val registrationButtonText = context.getString(R.string.registration)
        dropdownMenuItems = listOf(homePageButtonText, aboutMeButtonText, contactButtonText, loginButtonText, registrationButtonText)
        dropdownItemDestinations = listOf(
            HomeScreen,
            AboutMeScreen,
            ContactScreen,
            LogInScreen,
            RegistrationScreen
        )
        when (currentRoute) {
            HomeScreen.route -> {
                dropdownMenuItems = dropdownMenuItems.filterNot { it == homePageButtonText }
                dropdownItemDestinations = dropdownItemDestinations.filterNot { it.route == currentBackStackEntry?.destination?.route }
            }
            AboutMeScreen.route -> {
                dropdownMenuItems = dropdownMenuItems.filterNot { it == aboutMeButtonText }
                dropdownItemDestinations = dropdownItemDestinations.filterNot { it.route == currentBackStackEntry?.destination?.route }
            }
            ContactScreen.route -> {
                dropdownMenuItems = dropdownMenuItems.filterNot { it == contactButtonText }
                dropdownItemDestinations = dropdownItemDestinations.filterNot { it.route == currentBackStackEntry?.destination?.route }
            }
            LogInScreen.route -> {
                dropdownMenuItems = dropdownMenuItems.filterNot { it == loginButtonText }
                dropdownItemDestinations = dropdownItemDestinations.filterNot { it.route == currentBackStackEntry?.destination?.route }
            }
            RegistrationScreen.route -> {
                dropdownMenuItems = dropdownMenuItems.filterNot { it == registrationButtonText }
                dropdownItemDestinations = dropdownItemDestinations.filterNot { it.route == currentBackStackEntry?.destination?.route }
            }
        }
    }

    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = false },
        modifier = Modifier.background(Cream)
    ) {
        repeat(dropdownMenuItems.size) {
                index ->
            DropdownMenuItem(
                onClick = {
                    navHostController.navigate(dropdownItemDestinations[index].route) {
                        popUpTo(0)
                    }
                },
                text = { Text(dropdownMenuItems[index]) },
                modifier = Modifier.background(Cream)
            )
            Divider()
        }
    }
}
