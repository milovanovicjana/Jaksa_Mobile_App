
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jaksaapp.R
import com.example.jaksaapp.ui.theme.BrownNavbar
import com.example.jaksaapp.ui.theme.Cream
import com.example.jaksaapp.ui.theme.elements.TopNavBar
import java.util.Date

@SuppressLint("MutableCollectionMutableState")
@Composable
fun ClassScheduleScreen(isLoggedIn: Boolean, navHostController: NavHostController) {
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
                    text = context.getString(R.string.class_schedule_page),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrownNavbar,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(20.dp))

                var currentMonth by remember { mutableStateOf(getFirstDayOfMonth(Date())) }
                val calendarDays = remember(currentMonth) {
                    generateCalendarDays(currentMonth)
                }

                CalendarView(
                    month = currentMonth,
                    date = calendarDays,
                    displayNext = true,
                    displayPrev = true,
                    onClickNext = {
                        currentMonth = getNextMonth(currentMonth)
                    },
                    onClickPrev = {
                        currentMonth = getPreviousMonth(currentMonth)
                    },
                    onClick = { clickedDate ->
                        Log.d("CalendarScreen", "Date clicked: $clickedDate")
                    },
                    startFromSunday = true
                )
            }
        }
    )
}
