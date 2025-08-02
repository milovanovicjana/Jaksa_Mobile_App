package com.example.jaksaapp.ui.theme.elements

import CustomText
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jaksaapp.ui.theme.BrownNavbar
import com.example.jaksaapp.ui.theme.Chocolate
import com.example.jaksaapp.viewModels.ClassViewModel
import isSameDay
import dateParser
import timeFormatter
import timeParser
import java.time.LocalTime
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ClassesForDate(selectedDate: Date, classViewModel: ClassViewModel) {
    val classesForDate = classViewModel.classes.filter {
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
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(4.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {

                        val start = LocalTime.parse(cl.timeStart, timeParser)
                        val durationInMinutes = (cl.duration.removeSuffix("h").toFloat() * 60).toLong()
                        val end = start.plusMinutes(durationInMinutes)
                        Text(
                            text = "${start.format(timeFormatter)} – ${end.format(timeFormatter)}h",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Chocolate
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = cl.description,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.DarkGray
                        )

                        Text(
                            text = "${cl.studentFirstName} ${cl.studentLastName}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = BrownNavbar
                        )
                    }
                }
            }
        }
    }
}
