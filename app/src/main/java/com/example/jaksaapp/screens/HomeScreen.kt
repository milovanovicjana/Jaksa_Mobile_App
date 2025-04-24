
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jaksaapp.R
import com.example.jaksaapp.elements.TopNavBar
import com.example.jaksaapp.ui.theme.BrownNavbar
import com.example.jaksaapp.ui.theme.LightBrown

@Composable
fun HomeScreen(isLoggedIn: Boolean, navHostController: NavHostController) {
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
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Image(
                    painter = painterResource(id = R.drawable.home_screen_picture),
                    contentDescription = null,
                    modifier = Modifier
                        .border(width = 10.dp, color = BrownNavbar)
                )

                CustomText(text = context.getString(R.string.home_screen_title))

                CustomText(
                    text = context.getString(R.string.home_screen_school_description),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal
                )

                CustomText(text = context.getString(R.string.home_screen_name))

                Spacer(modifier = Modifier.weight(1f))

                StatisticSection()
            }
        }
    )
}
