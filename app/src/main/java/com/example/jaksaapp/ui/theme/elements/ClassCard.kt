package com.example.jaksaapp.ui.theme.elements

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.WatchLater
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jaksaapp.remote.dto.ClassDto
import com.example.jaksaapp.remote.dto.ClassStatus
import com.example.jaksaapp.ui.theme.BrownNavbar
import com.example.jaksaapp.ui.theme.Chocolate
import com.example.jaksaapp.ui.theme.Green2
import com.example.jaksaapp.ui.theme.Red
import timeFormatter
import timeParser
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ClassCard(
    cl: ClassDto,
    selectedTabIndex: Int,
    onAcceptClick: (ClassDto) -> Unit,
    onRejectClick: (ClassDto) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                val start = LocalTime.parse(cl.timeStart, timeParser)
                val durationInMinutes = (cl.duration.removeSuffix("h").toFloat() * 60).toLong()
                val end = start.plusMinutes(durationInMinutes)

                Text(
                    text = "Datum: ${cl.date}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Chocolate
                )

                Text(
                    text = "Vreme: ${start.format(timeFormatter)}h – ${end.format(timeFormatter)}h",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Chocolate
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "${cl.studentFirstName} ${cl.studentLastName}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrownNavbar
                )

                Text(
                    text = cl.description,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.DarkGray
                )

                if (selectedTabIndex == 1 && cl.classStatus == ClassStatus.PENDING) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedButton(
                            onClick = { onAcceptClick(cl) },
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = Green2
                            ),
                            modifier = Modifier.widthIn(min = 80.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Done,
                                contentDescription = "Accept",
                                tint = Green2,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.size(4.dp))
                            Text("Prihvati", fontSize = 14.sp)
                        }

                        OutlinedButton(
                            onClick = { onRejectClick(cl) },
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = Red
                            ),
                            modifier = Modifier.widthIn(min = 80.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Cancel,
                                contentDescription = "Reject",
                                tint = Red,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.size(4.dp))
                            Text("Odbij", fontSize = 14.sp)
                        }
                    }
                }
            }

            when (cl.classStatus) {
                ClassStatus.APPROVED -> {
                    Icon(
                        imageVector = Icons.Default.Done,
                        contentDescription = "Approved",
                        tint = Green2,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(12.dp)
                            .size(20.dp)
                    )
                }
                ClassStatus.REJECTED -> {
                    Icon(
                        imageVector = Icons.Default.Cancel,
                        contentDescription = "Rejected",
                        tint = Red,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(12.dp)
                            .size(20.dp)
                    )
                }
                ClassStatus.PENDING -> {
                    Icon(
                        imageVector = Icons.Default.WatchLater,
                        contentDescription = "Pending",
                        tint = Color.DarkGray,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(12.dp)
                            .size(20.dp)
                    )
                }
            }
        }
    }
}
