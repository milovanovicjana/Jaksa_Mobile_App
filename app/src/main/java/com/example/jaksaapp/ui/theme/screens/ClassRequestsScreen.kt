
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jaksaapp.R
import com.example.jaksaapp.dataClasses.ClassRequest
import com.example.jaksaapp.ui.theme.BrownNavbar
import com.example.jaksaapp.ui.theme.Cream
import com.example.jaksaapp.ui.theme.Green2
import com.example.jaksaapp.ui.theme.Red
import com.example.jaksaapp.ui.theme.elements.TopNavBar

@Composable
fun ClassRequestsScreen(isLoggedIn: Boolean, navHostController: NavHostController) {
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
                    .background(Cream).padding(vertical = 10.dp, horizontal = 40.dp).verticalScroll(
                        rememberScrollState()
                    ),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                CustomText(
                    text = context.getString(R.string.class_requests_page),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrownNavbar,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(20.dp))
                val requests = listOf(
                    ClassRequest(
                        name = "Maja Nikolic",
                        date = "17.12.2024.",
                        time = "18:00",
                        duration = "1.5h"
                    ),
                    ClassRequest(
                        name = "Ana Antic",
                        date = "22.12.2024.",
                        time = "19:00",
                        duration = "1.5h"
                    ),
                    ClassRequest(
                        name = "Jana Milic",
                        date = "23.12.2024.",
                        time = "15:00",
                        duration = "1.5h"
                    )
                )

                requests.forEach { request ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(request.name, modifier = Modifier.weight(2f))

                        // Clicking the info button will display the date, time, and duration
                        Icon(imageVector = Icons.Filled.Info, contentDescription = "", tint = BrownNavbar, modifier = Modifier.weight(1f))

                        Icon(imageVector = Icons.Filled.Done, contentDescription = "", tint = Green2, modifier = Modifier.weight(1f))

                        Icon(imageVector = Icons.Filled.Cancel, contentDescription = "", tint = Red, modifier = Modifier.weight(1f))
                    }

                    Divider(color = Color.LightGray)
                }
            }
        }
    )
}
