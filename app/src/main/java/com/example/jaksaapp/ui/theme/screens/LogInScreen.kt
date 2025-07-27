
import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.jaksaapp.R
import com.example.jaksaapp.TokenManager
import com.example.jaksaapp.remote.dto.LoginRequest
import com.example.jaksaapp.ui.theme.BrownNavbar
import com.example.jaksaapp.ui.theme.Cream
import com.example.jaksaapp.ui.theme.Green1
import com.example.jaksaapp.ui.theme.elements.TopNavBar
import com.example.jaksaapp.viewModels.AuthViewModel

@Composable
fun LogInScreen(
    isLoggedIn: Boolean,
    navHostController: NavHostController,
    authViewModel: AuthViewModel = viewModel(),
    onLoginSuccess: () -> Unit
) {
    val context = LocalContext.current
    val loginResult = authViewModel.loginResult
    val tokenManager = remember { TokenManager(context) }

    Scaffold(
        topBar = {
            TopNavBar(isLoggedIn, navHostController)
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(Cream).padding(vertical = 10.dp, horizontal = 40.dp).verticalScroll(
                        rememberScrollState()
                    ),
                horizontalAlignment = Alignment.CenterHorizontally

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

                var username by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                var isPasswordVisible by remember { mutableStateOf(false) }

                val focusManager = LocalFocusManager.current

                CustomInputField(
                    focusManager,
                    value = username,
                    label = context.getString(R.string.user_name),
                    onValueChange = { username = it },
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
                    val validationMessage: String = authViewModel.validateLoginFields(username, password)

                    if (validationMessage.isEmpty()) {
                        val request = LoginRequest(
                            username = username,
                            password = password
                        )
                        authViewModel.loginUser(request)
                    } else {
                        Toast.makeText(context, validationMessage, Toast.LENGTH_LONG).show()
                    }
                }, modifier = Modifier.align(Alignment.CenterHorizontally).padding(10.dp))

                LaunchedEffect(loginResult) {
                    loginResult?.let {
                        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                        if (it.contains("Uspesno", ignoreCase = true)) {
                            authViewModel.authToken?.let { token ->
                                tokenManager.saveToken(token)
                            }
                            onLoginSuccess()
                            navHostController.navigate(com.example.jaksaapp.HomeScreen.route) {
                                popUpTo(0)
                            }
                        }
                    }
                }
            }
        }
    )
}
