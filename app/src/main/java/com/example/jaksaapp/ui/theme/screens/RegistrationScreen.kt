
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.jaksaapp.remote.dto.RegisterRequest
import com.example.jaksaapp.remote.dto.Role
import com.example.jaksaapp.ui.theme.BrownNavbar
import com.example.jaksaapp.ui.theme.Cream
import com.example.jaksaapp.ui.theme.Green1
import com.example.jaksaapp.ui.theme.elements.TopNavBar
import com.example.jaksaapp.viewModels.AuthViewModel

@Composable
fun RegistrationScreen(isLoggedIn: Boolean, navHostController: NavHostController, authViewModel: AuthViewModel = viewModel()) {
    val context = LocalContext.current
    val registrationResult = authViewModel.registrationResult

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
                CustomText(
                    text = context.getString(R.string.app_name),
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    color = Green1,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                CustomText(
                    text = context.getString(R.string.registration),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrownNavbar,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                var firstname by remember { mutableStateOf("") }
                var lastname by remember { mutableStateOf("") }
                var username by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                var isPasswordVisible by remember { mutableStateOf(false) }
                var email by remember { mutableStateOf("") }
                var phone by remember { mutableStateOf("") }

                val focusManager = LocalFocusManager.current

                CustomInputField(
                    focusManager,
                    value = firstname,
                    label = context.getString(R.string.name),
                    onValueChange = { firstname = it },
                    modifier = Modifier.fillMaxWidth()
                )
                CustomInputField(
                    focusManager,
                    value = lastname,
                    label = context.getString(R.string.surname),
                    onValueChange = { lastname = it },
                    modifier = Modifier.fillMaxWidth()
                )
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
                CustomInputField(
                    focusManager,
                    value = email,
                    label = context.getString(R.string.email_label),
                    onValueChange = { email = it },
                    modifier = Modifier.fillMaxWidth()
                )
                CustomInputField(
                    focusManager,
                    value = phone,
                    label = context.getString(R.string.phone_number_label),
                    onValueChange = { phone = it },
                    modifier = Modifier.fillMaxWidth()
                )

                CustomButton(text = context.getString(R.string.registration_button), onClick = {
                    val validationMessage: String = authViewModel.validateRegistrationFields(
                        firstname,
                        lastname,
                        email,
                        phone,
                        username,
                        password
                    )

                    if (validationMessage.isEmpty()) {
                        val request = RegisterRequest(
                            firstname = firstname,
                            lastname = lastname,
                            email = email,
                            phone = phone,
                            username = username,
                            password = password,
                            role = Role.STUDENT
                        )
                        authViewModel.registerUser(request)
                    } else {
                        Toast.makeText(context, validationMessage, Toast.LENGTH_LONG).show()
                    }
                }, modifier = Modifier.align(Alignment.CenterHorizontally).padding(10.dp))

                LaunchedEffect(registrationResult) {
                    registrationResult?.let {
                        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                        if (it.contains("Uspesna", ignoreCase = true)) {
                            navHostController.navigate(com.example.jaksaapp.LogInScreen.route) {
                                popUpTo(0)
                            }
                        }
                    }
                }
            }
        }
    )
}
