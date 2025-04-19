
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jaksaapp.R
import com.example.jaksaapp.elements.TopNavBar
import com.example.jaksaapp.ui.theme.BrownNavbar
import com.example.jaksaapp.ui.theme.LightBrown

@Composable
fun HomeScreen(isLoggedIn: Boolean) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopNavBar(isLoggedIn)
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

                Text(
                    text = context.getString(R.string.home_screen_title),
                    style = TextStyle(
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold

                    ),
                    color = Color.Black,
                    modifier = Modifier.padding(20.dp)
                )

                Text(
                    text = context.getString(R.string.home_screen_school_description),
                    style = TextStyle(
                        fontSize = 15.sp,
                        textAlign = TextAlign.Justify

                    ),
                    color = Color.Black,
                    modifier = Modifier.padding(20.dp)
                )

                Text(
                    text = context.getString(R.string.home_screen_name),
                    style = TextStyle(
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.Black,
                    modifier = Modifier.padding(20.dp)
                )
                Spacer(modifier = Modifier.weight(1f))

                StatisticSection()
            }
        }
    )
}
