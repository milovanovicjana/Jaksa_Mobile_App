import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jaksaapp.R
import com.example.jaksaapp.ui.theme.Green1
import com.example.jaksaapp.ui.theme.Green2
import com.example.jaksaapp.ui.theme.Green3
import com.example.jaksaapp.ui.theme.Green4
import com.example.jaksaapp.ui.theme.Green5
import com.example.jaksaapp.ui.theme.Green6

@Composable
fun StatisticSection() {
    Row(
        modifier = Modifier.horizontalScroll(
            rememberScrollState()
        )
    ) {
        val statisticElements = listOf(
            StatisticElement(
                icon = R.drawable.education_icon,
                value = "1500+",
                label = "zadovoljnih korisnika",
                backgroundColor = Green1
            ),
            StatisticElement(
                icon = R.drawable.experience_icon,
                value = "30+",
                label = "godina iskustva",
                backgroundColor = Green2
            ),
            StatisticElement(icon = R.drawable.book_icon, value = "10+", label = "napisanih zbirki", backgroundColor = Green3),
            StatisticElement(
                icon = R.drawable.check_icon,
                value = "88%",
                label = "prolaznost na ispitu",
                backgroundColor = Green4
            ),
            StatisticElement(
                icon = R.drawable.students_icon,
                value = "max 7",
                label = "studenata u grupi",
                backgroundColor = Green5
            ),
            StatisticElement(icon = R.drawable.location_icon, value = "centralna", label = "lokacija", backgroundColor = Green6)

        )

        repeat(statisticElements.size) { index ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.background(statisticElements[index].backgroundColor).size(150.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Icon(
                        painter = painterResource(statisticElements[index].icon),
                        contentDescription = "Phone Icon",
                        modifier = Modifier.size(55.dp).padding(5.dp)
                            .clickable {
                            },
                        tint = Color.White
                    )
                    Text(
                        statisticElements[index].value,
                        style = TextStyle(
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.White,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        statisticElements[index].label,
                        style = TextStyle(
                            fontSize = 13.sp
                        ),
                        color = Color.White,
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }
        }
    }
}
