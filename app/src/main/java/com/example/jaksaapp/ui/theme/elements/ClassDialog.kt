package com.example.jaksaapp.ui.theme.elements

import CustomText
import DialogState
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jaksaapp.remote.dto.ClassRequest
import com.example.jaksaapp.remote.dto.Role
import com.example.jaksaapp.ui.theme.BrownNavbar
import com.example.jaksaapp.ui.theme.Chocolate
import com.example.jaksaapp.ui.theme.Cream
import com.example.jaksaapp.viewModels.ClassViewModel
import com.example.jaksaapp.viewModels.UserViewModel
import formatToDateStringDisplay
import formatToDateString
import timeFormatter
import java.time.LocalTime
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ClassDialog(
    dialogState: DialogState,
    selectedDate: Date,
    classViewModel: ClassViewModel,
    userViewModel: UserViewModel,
    onDialogStateChange: (DialogState?) -> Unit
) {
    val context = LocalContext.current

    var startTime by remember { mutableStateOf<LocalTime?>(null) }
    var description by remember { mutableStateOf("") }
    var durationOption by remember { mutableStateOf("1h") }

    AlertDialog(
        onDismissRequest = { onDialogStateChange(null) },
        title = {
            when (dialogState) {
                DialogState.INITIAL -> {
                    CustomText(
                        text = selectedDate.formatToDateStringDisplay(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Chocolate,
                        padding = 0.dp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                DialogState.VIEW_CLASSES -> {
                    CustomText(
                        text = " Zakazani časovi",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Chocolate,
                        padding = 0.dp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()

                    )
                }
                DialogState.ADD_CLASS -> {
                    CustomText(
                        text = " Zakazi novi čas",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Chocolate,
                        padding = 0.dp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()

                    )
                }
            }
        },
        text = {
            when (dialogState) {
                DialogState.VIEW_CLASSES -> {
                    ClassesForDate(selectedDate, classViewModel)
                }
                DialogState.ADD_CLASS -> {
                    CreateClassRequest(
                        selectedDate,
                        startTime,
                        description,
                        durationOption,
                        { startTime = it },
                        { description = it },
                        { durationOption = it }
                    )
                }
                else -> {}
            }
        },
        confirmButton = {
            when (dialogState) {
                DialogState.INITIAL -> {
                    TextButton(onClick = {
                        onDialogStateChange(DialogState.VIEW_CLASSES)
                    }) {
                        CustomText(
                            text = "Prikazi časove",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = BrownNavbar,
                            padding = 0.dp
                        )
                    }
                }
                DialogState.ADD_CLASS -> {
                    TextButton(onClick = {
                        onDialogStateChange(DialogState.INITIAL)
                    }) {
                        CustomText(
                            text = "Nazad",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = BrownNavbar,
                            padding = 0.dp
                        )
                    }
                }
                else -> {}
            }
        },
        dismissButton = {
            when (dialogState) {
                DialogState.INITIAL -> {
                    TextButton(onClick = {
                        onDialogStateChange(DialogState.ADD_CLASS)
                    }) {
                        CustomText(
                            text = "Zakazi novi čas",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = BrownNavbar,
                            padding = 0.dp
                        )
                    }
                }
                DialogState.ADD_CLASS -> {
                    TextButton(onClick = {
                        val validationMessage: String = classViewModel.validateAddClassRequestFields(
                            selectedDate,
                            startTime,
                            durationOption,
                            description
                        )

                        if (validationMessage.isEmpty()) {
                            val dateString = selectedDate.formatToDateString()
                            val startString = startTime!!.format(timeFormatter)

                            val request = ClassRequest(
                                date = dateString,
                                timeStart = startString,
                                duration = durationOption,
                                description = description,
                                studentId = userViewModel.loggedInUser!!.id ?: 1,
                                requestedByStudent = userViewModel.loggedInUser!!.role==Role.STUDENT
                            )

                            classViewModel.createClassRequest(
                                request
                            )

                            // clear fields
                            startTime = null
                            description = ""
                            durationOption = "1h"

                            onDialogStateChange(DialogState.INITIAL)
                        } else {
                            Toast.makeText(context, validationMessage, Toast.LENGTH_LONG).show()
                        }
                    }) {
                        CustomText(
                            text = "Zakazi",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = BrownNavbar,
                            padding = 0.dp
                        )
                    }
                }
                else -> {
                    TextButton(onClick = {
                        onDialogStateChange(DialogState.INITIAL)
                    }) {
                        CustomText(
                            text = "Nazad",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = BrownNavbar,
                            padding = 0.dp
                        )
                    }
                }
            }
        },
        containerColor = Cream
    )
}
