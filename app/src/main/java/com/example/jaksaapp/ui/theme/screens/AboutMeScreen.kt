
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jaksaapp.R
import com.example.jaksaapp.ui.theme.Cream
import com.example.jaksaapp.ui.theme.elements.TopNavBar

@Composable
fun AboutMeScreen(isLoggedIn: Boolean, navHostController: NavHostController) {
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
                ImageSlider()

                CustomText(text = context.getString(R.string.about_me_title_1), padding = 10.dp)

                CustomText(
                    text = context.getString(R.string.about_me_text),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    padding = 10.dp
                )

                CustomText(text = context.getString(R.string.about_me_title_2), padding = 10.dp)

                CustomText(
                    text = context.getString(R.string.about_me_methods),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    padding = 10.dp
                )

                val methodsList = listOf(
                    context.getString(R.string.method_bullet_1),
                    context.getString(R.string.method_bullet_2),
                    context.getString(R.string.method_bullet_3)
                )

                BulletList(methodsList)

                CustomText(text = context.getString(R.string.about_me_title_3), padding = 10.dp)

                val educationList = listOf(
                    context.getString(R.string.education_bullet_1),
                    context.getString(R.string.education_bullet_2),
                    context.getString(R.string.education_bullet_3),
                    context.getString(R.string.education_bullet_4)
                )

                BulletList(educationList)

                Spacer(modifier = Modifier.weight(1f))
            }
        }
    )
}
