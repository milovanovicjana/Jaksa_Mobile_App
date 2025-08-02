
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.jaksaapp.R
import com.example.jaksaapp.TokenManager
import com.example.jaksaapp.remote.dto.ClassStatus
import com.example.jaksaapp.remote.dto.Role
import com.example.jaksaapp.ui.theme.BrownNavbar
import com.example.jaksaapp.ui.theme.Cream
import com.example.jaksaapp.ui.theme.elements.ClassCard
import com.example.jaksaapp.ui.theme.elements.RequestFilters
import com.example.jaksaapp.ui.theme.elements.RequestsSelector
import com.example.jaksaapp.ui.theme.elements.TopNavBar
import com.example.jaksaapp.viewModels.ClassViewModel
import com.example.jaksaapp.viewModels.UserViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ClassRequestsScreen(
    isLoggedIn: Boolean,
    navHostController: NavHostController,
    classViewModel: ClassViewModel = viewModel(),
    userViewModel: UserViewModel = viewModel()
) {
    val context = LocalContext.current
    val tokenManager = TokenManager(context)

    var selectedTabIndex by remember { mutableStateOf(0) }
    val titles = listOf("Poslati", "Pristigli")
    val statuses = listOf(ClassStatus.PENDING, ClassStatus.APPROVED, ClassStatus.REJECTED)
    var selectedStatus by remember { mutableStateOf(ClassStatus.PENDING) }

    var isStudent by remember { mutableStateOf(false) }
    val isSentTab = selectedTabIndex == 0
    val requestedByStudent = if (isStudent) isSentTab else !isSentTab

    var filteredRequests = classViewModel.classRequests
        .filter { it.requestedByStudent == requestedByStudent }

    if (isSentTab) {
        filteredRequests = filteredRequests.filter { it.classStatus == selectedStatus }
    }

    LaunchedEffect(Unit) {
        val token = tokenManager.getToken()
        classViewModel.setToken(token)
        userViewModel.setToken(token)
        userViewModel.getLoggedInUser()
        userViewModel.loggedInUserFlow.collect { user ->
            if (user != null) {
                if (user.role == Role.TEACHER) {
                    classViewModel.getAllClasses()
                    isStudent = false
                } else {
                    isStudent = true
                    user.id?.let { classViewModel.getClassesForUser(it) }
                }
            }
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
                CustomText(
                    text = context.getString(R.string.class_requests_page),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrownNavbar,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(20.dp))

                RequestsSelector(selectedTabIndex, { selectedTabIndex = it }, titles)
                if (selectedTabIndex == 0) {
                    RequestFilters(statuses, selectedStatus, { selectedStatus = it })
                }
                if (filteredRequests.isEmpty()) {
                    CustomText(
                        text = "Nema zakazanih časova.",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = BrownNavbar,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                filteredRequests.forEach { cl ->

                    ClassCard(
                        cl,
                        selectedTabIndex,
                        onAcceptClick = {
                            classViewModel.acceptRequest(
                                cl.id!!,
                                userViewModel.loggedInUser!!.id!!,
                                userViewModel.loggedInUser!!.role == Role.TEACHER
                            )
                        },
                        onRejectClick = {
                            classViewModel.rejectRequest(
                                cl.id!!,
                                userViewModel.loggedInUser!!.id!!,
                                userViewModel.loggedInUser!!.role == Role.TEACHER
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    )
}
