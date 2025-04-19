import androidx.compose.foundation.background
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.jaksaapp.R
import com.example.jaksaapp.ui.theme.Cream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuTopBar(expanded: MutableState<Boolean>, isLoggedIn: Boolean) {
    val context = LocalContext.current

    val dropdownMenuItems: List<String>
    if (isLoggedIn) {
        val myProfileButtonText = context.getString(R.string.my_profile)
        val logOutButtonText = context.getString(R.string.logout)
        dropdownMenuItems = listOf(myProfileButtonText, logOutButtonText)
    } else {
        val loginButtonText = context.getString(R.string.login)
        val registrationButtonText = context.getString(R.string.registration)
        dropdownMenuItems = listOf(loginButtonText, registrationButtonText)
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
                },
                text = { Text(dropdownMenuItems[index]) },
                modifier = Modifier.background(Cream)
            )
            Divider()
        }
    }
}
