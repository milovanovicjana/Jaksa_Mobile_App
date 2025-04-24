
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jaksaapp.HomeScreen
import com.example.jaksaapp.R
import com.example.jaksaapp.elements.TopNavBar
import com.example.jaksaapp.ui.theme.BrownNavbar
import com.example.jaksaapp.ui.theme.Cream
import com.example.jaksaapp.ui.theme.LightBrown

@Composable
fun ContactScreen(isLoggedIn: Boolean, navHostController: NavHostController) {
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
                    .background(LightBrown).padding(10.dp).verticalScroll(
                        rememberScrollState()
                    ),
                horizontalAlignment = Alignment.Start

            ) {
                CustomText(text = context.getString(R.string.contact), padding = 10.dp, color = Color.White)

                Spacer(modifier = Modifier.height(20.dp))

                CustomText(text = context.getString(R.string.email_label), fontSize = 17.sp, padding = 10.dp, color = Color.White)
                CustomText(
                    text = context.getString(R.string.email_value),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    padding = 10.dp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(15.dp))

                CustomText(text = context.getString(R.string.phone_number_label), fontSize = 17.sp, padding = 10.dp, color = Color.White)
                CustomText(
                    text = context.getString(R.string.phone_number_value),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    padding = 10.dp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(15.dp))

                CustomText(text = context.getString(R.string.address_label), fontSize = 17.sp, padding = 10.dp, color = Color.White)
                CustomText(
                    text = context.getString(R.string.address_value),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    padding = 10.dp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(15.dp))

                var name by remember { mutableStateOf("") }
                var surname by remember { mutableStateOf("") }
                var text by remember { mutableStateOf("") }
                val focusManager = LocalFocusManager.current

                CustomInputField(
                    focusManager,
                    value = name,
                    label = context.getString(R.string.name),
                    onValueChange = { name = it },
                    modifier = Modifier.fillMaxWidth()
                )

                CustomInputField(
                    focusManager,
                    value = surname,
                    label = context.getString(R.string.surname),
                    onValueChange = { surname = it },
                    modifier = Modifier.fillMaxWidth()
                )

                CustomInputField(
                    focusManager,
                    value = text,
                    label = context.getString(R.string.text),
                    onValueChange = { text = it },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(15.dp))

                CustomButton(text = context.getString(R.string.send_button), onClick = {
                    navHostController.navigate(HomeScreen.route) {
                        popUpTo(0)
                    }
                }, containerColor = Cream, textColor = BrownNavbar, modifier = Modifier.align(Alignment.CenterHorizontally))

                Spacer(modifier = Modifier.weight(1f))
            }
        }
    )
}
