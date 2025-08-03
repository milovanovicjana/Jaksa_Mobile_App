package com.example.jaksaapp.ui.theme.elements

import CustomText
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jaksaapp.remote.dto.Role
import com.example.jaksaapp.ui.theme.BrownNavbar
import com.example.jaksaapp.viewModels.ClassViewModel
import com.example.jaksaapp.viewModels.UserViewModel
import dateParser
import isSameDay
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ClassesForDate(selectedDate: Date, classViewModel: ClassViewModel, userViewModel: UserViewModel) {
    val classesForDate = classViewModel.classesForMonth.filter {
        isSameDay(dateParser(it.date), selectedDate!!)
    }
    val sortedClasses = classesForDate.sortedBy { it.timeStart }

    if (sortedClasses.isEmpty()) {
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
    } else {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            sortedClasses.forEach { cl ->
                ClassCard(cl, 0, {}, {},{
                    classViewModel.deleteClass(
                        cl.id!!,
                        userViewModel.loggedInUser!!.id!!,
                        userViewModel.loggedInUser!!.role == Role.TEACHER
                    )
                },userViewModel.loggedInUser!!.id,userViewModel.loggedInUser!!.role == Role.TEACHER) //dodatu
            }
        }
    }
}
