
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jaksaapp.R
import com.example.jaksaapp.elements.TopNavBar
import com.example.jaksaapp.ui.theme.BrownNavbar
import com.example.jaksaapp.ui.theme.Cream
import com.example.jaksaapp.ui.theme.Green1

@Composable
fun LogInScreen(isLoggedIn: Boolean, navHostController: NavHostController) {
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
                Spacer(modifier = Modifier.height(40.dp))

                CustomText(
                    text = context.getString(R.string.app_name),
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    color = Green1,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(40.dp))

                CustomText(
                    text = context.getString(R.string.login),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrownNavbar,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                var user_name by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                var isPasswordVisible by remember { mutableStateOf(false) }

                val focusManager = LocalFocusManager.current

                CustomInputField(
                    focusManager,
                    value = user_name,
                    label = context.getString(R.string.user_name),
                    onValueChange = { user_name = it },
                    modifier = Modifier.fillMaxWidth()
                )
                CustomPasswordInputField(
                    focusManager,
                    value = password,
                    label = context.getString(R.string.password),
                    onValueChange = { password = it },
                    isPasswordVisible = isPasswordVisible,
                    onPasswordVisibilityChange = { isPasswordVisible = !isPasswordVisible },
                    modifier = Modifier.fillMaxWidth()
                )

                CustomButton(text = context.getString(R.string.login_button), onClick = {
                    navHostController.navigate(com.example.jaksaapp.HomeScreen.route) {
                        popUpTo(0)
                    }
                }, modifier = Modifier.align(Alignment.CenterHorizontally).padding(10.dp))
            }
        }
    )
}
