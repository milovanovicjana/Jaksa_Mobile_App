
import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.jaksaapp.R
import com.example.jaksaapp.TokenManager
import com.example.jaksaapp.remote.dto.ClassesByMonthRequest
import com.example.jaksaapp.ui.theme.BrownNavbar
import com.example.jaksaapp.ui.theme.Cream
import com.example.jaksaapp.ui.theme.elements.ClassDialog
import com.example.jaksaapp.ui.theme.elements.TopNavBar
import com.example.jaksaapp.viewModels.ClassViewModel
import com.example.jaksaapp.viewModels.UserViewModel
import java.util.Date

enum class DialogState {
    INITIAL,
    VIEW_CLASSES,
    ADD_CLASS
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("MutableCollectionMutableState")
@Composable
fun ClassScheduleScreen(
    isLoggedIn: Boolean,
    navHostController: NavHostController,
    classViewModel: ClassViewModel = viewModel(),
    userViewModel: UserViewModel = viewModel()
) {
    val context = LocalContext.current
    val tokenManager = TokenManager(context)

    var selectedDate by remember { mutableStateOf<Date?>(null) }
    var dialogState by remember { mutableStateOf<DialogState?>(null) }
    var currentDate by remember { mutableStateOf(getFirstDayOfMonth(Date())) }
    val calendarDays = remember(currentDate, classViewModel.classes) {
        generateCalendarDays(currentDate, classViewModel.classes)
    }
    val requestClassResult = classViewModel.requestClassResult

    LaunchedEffect(Unit) {
        val token = tokenManager.getToken()
        userViewModel.setToken(token)
        userViewModel.getLoggedInUser()
        classViewModel.setToken(token)
        classViewModel.getClassesForMonth(ClassesByMonthRequest(getYear(currentDate), getMonth(currentDate)))
    }
    LaunchedEffect(currentDate) {
        classViewModel.getClassesForMonth(ClassesByMonthRequest(getYear(currentDate), getMonth(currentDate)))
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
                CustomText(
                    text = context.getString(R.string.class_schedule_page),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrownNavbar,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(20.dp))

                CalendarView(
                    month = currentDate,
                    date = calendarDays,
                    displayNext = true,
                    displayPrev = true,
                    onClickNext = {
                        currentDate = getNextMonth(currentDate)
                    },
                    onClickPrev = {
                        currentDate = getPreviousMonth(currentDate)
                    },
                    onClick = { clickedDate ->
                        selectedDate = clickedDate
                        dialogState = DialogState.INITIAL
                    },
                    startFromSunday = true
                )

                if (dialogState != null && selectedDate != null) {
                    ClassDialog(
                        dialogState = dialogState!!,
                        selectedDate = selectedDate!!,
                        classViewModel = classViewModel,
                        userViewModel = userViewModel,
                        onDialogStateChange = { dialogState = it }
                    )
                }

                LaunchedEffect(requestClassResult) {
                    requestClassResult?.let {
                        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    )
}
