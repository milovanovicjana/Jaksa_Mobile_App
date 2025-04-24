
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jaksaapp.elements.TopNavBar
import com.example.jaksaapp.ui.theme.Cream

@Composable
fun RegistrationScreen(isLoggedIn: Boolean, navHostController: NavHostController) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopNavBar(isLoggedIn, navHostController)
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(Cream).padding(10.dp).verticalScroll(
                        rememberScrollState()
                    ),
                horizontalAlignment = Alignment.Start

            ) {
                Text("registration")
            }
        }
    )
}
