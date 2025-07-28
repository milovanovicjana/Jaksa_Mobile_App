
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.jaksaapp.remote.dto.UserDto
import com.example.jaksaapp.ui.theme.Chocolate
import com.example.jaksaapp.ui.theme.Cream
import com.example.jaksaapp.ui.theme.elements.TopNavBar
import com.example.jaksaapp.viewModels.UserViewModel

@Composable
fun MyProfileScreen(isLoggedIn: Boolean, navHostController: NavHostController, userViewModel: UserViewModel = viewModel()) {
    val context = LocalContext.current
    val tokenManager = TokenManager(context)
    val updateFieldsResult = userViewModel.updateFieldsResult

    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        val token = tokenManager.getToken()
        userViewModel.setToken(token)
        userViewModel.getLoggedInUser()
    }

    LaunchedEffect(userViewModel.loggedInUser) {
        userViewModel.loggedInUser?.let { user ->
            firstname = user.firstname
            lastname = user.lastname
            email = user.email
            phone = user.phone
            username = user.username
        }
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
                Spacer(modifier = Modifier.height(20.dp))

                CustomText(
                    text = context.getString(R.string.my_profile),
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    color = Chocolate,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(20.dp))

                val focusManager = LocalFocusManager.current

                var isNameFieldEnabled by remember { mutableStateOf(false) }
                var isSurnameFieldEnabled by remember { mutableStateOf(false) }
                var isPhoneFieldEnabled by remember { mutableStateOf(false) }

                CustomInputField(
                    focusManager,
                    value = firstname,
                    trailingIcon = {
                        IconButton(onClick = {
                            if (isNameFieldEnabled && firstname != (userViewModel.loggedInUser?.firstname ?: "")) {
                                val validationMessage: String = userViewModel.validateUpdateUserFields(
                                    firstname,
                                    lastname,
                                    phone
                                )
                                if (validationMessage.isEmpty()) {
                                    val request = UserDto(
                                        firstname = firstname,
                                        lastname = lastname,
                                        email = email,
                                        phone = phone,
                                        username = username
                                    )
                                    userViewModel.updateUser(request)
                                } else {
                                    firstname = userViewModel.loggedInUser?.firstname ?: ""
                                    Toast.makeText(context, validationMessage, Toast.LENGTH_LONG).show()
                                }
                            }
                            isNameFieldEnabled = !isNameFieldEnabled
                        }) {
                            Icon(
                                imageVector = if (isNameFieldEnabled) Icons.Filled.Done else Icons.Filled.Edit,
                                contentDescription = if (isNameFieldEnabled) "Save" else "Edit"
                            )
                        }
                    },
                    enabled = isNameFieldEnabled,
                    label = context.getString(R.string.name),
                    onValueChange = { firstname = it },
                    modifier = Modifier.fillMaxWidth()
                )

                CustomInputField(
                    focusManager,
                    value = lastname,
                    trailingIcon = {
                        IconButton(onClick = {
                            if (isSurnameFieldEnabled && lastname != (userViewModel.loggedInUser?.lastname ?: "")) {
                                val validationMessage: String = userViewModel.validateUpdateUserFields(
                                    firstname,
                                    lastname,
                                    phone
                                )
                                if (validationMessage.isEmpty()) {
                                    val request = UserDto(
                                        firstname = firstname,
                                        lastname = lastname,
                                        email = email,
                                        phone = phone,
                                        username = username
                                    )
                                    userViewModel.updateUser(request)
                                } else {
                                    lastname = userViewModel.loggedInUser?.lastname ?: ""
                                    Toast.makeText(context, validationMessage, Toast.LENGTH_LONG).show()
                                }
                            }

                            isSurnameFieldEnabled = !isSurnameFieldEnabled
                        }) {
                            Icon(
                                imageVector = if (isSurnameFieldEnabled) Icons.Filled.Done else Icons.Filled.Edit,
                                contentDescription = if (isSurnameFieldEnabled) "Save" else "Edit"
                            )
                        }
                    },
                    enabled = isSurnameFieldEnabled,
                    label = context.getString(R.string.surname),
                    onValueChange = { lastname = it },
                    modifier = Modifier.fillMaxWidth()
                )

                CustomInputField(
                    focusManager,
                    value = email,
                    label = context.getString(R.string.email_label),
                    enabled = false,
                    onValueChange = { email = it },
                    modifier = Modifier.fillMaxWidth()
                )

                CustomInputField(
                    focusManager,
                    value = phone,
                    trailingIcon = {
                        IconButton(onClick = {
                            if (isPhoneFieldEnabled && phone != (userViewModel.loggedInUser?.phone ?: "")) {
                                val validationMessage: String = userViewModel.validateUpdateUserFields(
                                    firstname,
                                    lastname,
                                    phone
                                )
                                if (validationMessage.isEmpty()) {
                                    val request = UserDto(
                                        firstname = firstname,
                                        lastname = lastname,
                                        email = email,
                                        phone = phone,
                                        username = username
                                    )
                                    userViewModel.updateUser(request)
                                } else {
                                    phone = userViewModel.loggedInUser?.phone ?: ""
                                    Toast.makeText(context, validationMessage, Toast.LENGTH_LONG).show()
                                }
                            }

                            isPhoneFieldEnabled = !isPhoneFieldEnabled
                        }) {
                            Icon(
                                imageVector = if (isPhoneFieldEnabled) Icons.Filled.Done else Icons.Filled.Edit,
                                contentDescription = if (isPhoneFieldEnabled) "Save" else "Edit"
                            )
                        }
                    },
                    enabled = isPhoneFieldEnabled,
                    label = context.getString(R.string.phone_number_label),
                    onValueChange = { phone = it },
                    modifier = Modifier.fillMaxWidth()
                )

                CustomInputField(
                    focusManager,
                    value = username,
                    label = context.getString(R.string.user_name),
                    enabled = false,
                    onValueChange = { username = it },
                    modifier = Modifier.fillMaxWidth()
                )

                CustomButton(text = context.getString(R.string.change_password), onClick = {

                    navHostController.navigate(com.example.jaksaapp.ChangePasswordScreen.route) {
                        popUpTo(0)
                    }
                }, modifier = Modifier.align(Alignment.CenterHorizontally).padding(10.dp))

                LaunchedEffect(updateFieldsResult) {
                    updateFieldsResult?.let {
                        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    )
}
