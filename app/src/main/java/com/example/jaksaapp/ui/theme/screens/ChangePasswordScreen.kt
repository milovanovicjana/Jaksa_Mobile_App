package com.example.jaksaapp.ui.theme.screens
import CustomButton
import CustomPasswordInputField
import CustomText
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
import com.example.jaksaapp.remote.dto.ChangePasswordRequest
import com.example.jaksaapp.remote.dto.LoginRequest
import com.example.jaksaapp.ui.theme.Chocolate
import com.example.jaksaapp.ui.theme.Cream
import com.example.jaksaapp.ui.theme.elements.TopNavBar
import com.example.jaksaapp.viewModels.UserViewModel

@Composable
fun ChangePasswordScreen(isLoggedIn: Boolean, navHostController: NavHostController, userViewModel: UserViewModel = viewModel()) {
    var oldPassword by remember { mutableStateOf("") }
    var isOldPasswordVisible by remember { mutableStateOf(false) }

    var newPassword by remember { mutableStateOf("") }
    var isNewPasswordVisible by remember { mutableStateOf(false) }

    var repeatedPassword by remember { mutableStateOf("") }
    var isRepeatedPasswordVisible by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val focusManager = LocalFocusManager.current
    val changePasswordResult = userViewModel.changePasswordResult
    val tokenManager = TokenManager(context)

    LaunchedEffect(Unit) {
        val token = tokenManager.getToken()
        userViewModel.setToken(token)
    }

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
                Spacer(modifier = Modifier.height(50.dp))

                CustomText(
                    text = context.getString(R.string.change_password_screen),
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    color = Chocolate,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(50.dp))

                CustomPasswordInputField(
                    focusManager,
                    value = oldPassword,
                    label = context.getString(R.string.old_password),
                    onValueChange = { oldPassword = it },
                    isPasswordVisible = isOldPasswordVisible,
                    onPasswordVisibilityChange = { isOldPasswordVisible = !isOldPasswordVisible },
                    modifier = Modifier.fillMaxWidth()
                )

                CustomPasswordInputField(
                    focusManager,
                    value = newPassword,
                    label = context.getString(R.string.new_password),
                    onValueChange = { newPassword = it },
                    isPasswordVisible = isNewPasswordVisible,
                    onPasswordVisibilityChange = { isNewPasswordVisible = !isNewPasswordVisible },
                    modifier = Modifier.fillMaxWidth()
                )

                CustomPasswordInputField(
                    focusManager,
                    value = repeatedPassword,
                    label = context.getString(R.string.repeated_password),
                    onValueChange = { repeatedPassword = it },
                    isPasswordVisible = isRepeatedPasswordVisible,
                    onPasswordVisibilityChange = { isRepeatedPasswordVisible = !isRepeatedPasswordVisible },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))

                CustomButton(text = context.getString(R.string.save_password), onClick = {
                    val validationMessage: String = userViewModel.validateChangePasswordFields(oldPassword, newPassword,repeatedPassword)

                    if (validationMessage.isEmpty()) {
                        val request = ChangePasswordRequest(
                            currentPassword = oldPassword,
                             newPassword = newPassword
                        )
                        userViewModel.changePassword(request)
                    } else {
                        Toast.makeText(context, validationMessage, Toast.LENGTH_LONG).show()
                    }
                }, modifier = Modifier.align(Alignment.CenterHorizontally).padding(10.dp))

                LaunchedEffect(changePasswordResult) {
                    changePasswordResult?.let {
                        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                        if (it.contains("Uspesno", ignoreCase = true)) {
                            navHostController.navigate(com.example.jaksaapp.MyProfileScreen.route) {
                                    popUpTo(0)
                            }
                        }
                    }
                }

            }
        }
    )
}
