package com.example.jaksaapp.ui.theme.elements

import CustomInputField
import CustomText
import android.app.TimePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jaksaapp.remote.dto.Role
import com.example.jaksaapp.ui.theme.BrownNavbar
import com.example.jaksaapp.ui.theme.Chocolate
import com.example.jaksaapp.ui.theme.LightBrown
import com.example.jaksaapp.viewModels.UserViewModel
import formatToDateStringDisplay
import timeFormatter
import java.time.LocalTime
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreateClassRequest(
    selectedDate: Date,
    startTime: LocalTime?,
    description: String,
    durationOption: String,
    studentId: Long,
    onStartTimeChange: (LocalTime?) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onDurationChange: (String) -> Unit,
    onStudentChange: (Long) -> Unit,
    userViewModel: UserViewModel
) {
    val context = LocalContext.current

    val focusManager = LocalFocusManager.current
    val showTimePicker = remember { mutableStateOf(false) }

    Column {
        CustomText(
            text = "Datum:",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = BrownNavbar,
            padding = 0.dp
        )
        Spacer(modifier = Modifier.height(8.dp))

        CustomText(
            text = selectedDate.formatToDateStringDisplay(),
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Chocolate,
            padding = 0.dp
        )

        Spacer(modifier = Modifier.height(8.dp))

        CustomText(
            text = "Vreme:",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = BrownNavbar,
            padding = 0.dp
        )

        TextButton(onClick = { showTimePicker.value = true }) {
            CustomText(
                text = startTime?.format(timeFormatter) ?: "Izaberi vreme",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Chocolate,
                padding = 0.dp
            )
        }

        if (showTimePicker.value) {
            TimePickerDialog(
                context,
                { _, hour: Int, minute: Int ->
                    onStartTimeChange(LocalTime.of(hour, minute))
                    showTimePicker.value = false
                },
                12,
                0,
                true
            ).show()
        }

        Spacer(modifier = Modifier.height(8.dp))

        CustomText(
            text = "Trajanje:",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = BrownNavbar,
            padding = 0.dp
        )
        Row {
            listOf("1h", "1.5h").forEach { option ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = durationOption == option,
                        onClick = { onDurationChange(option) },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Chocolate,
                            unselectedColor = LightBrown
                        )
                    )
                    Text(option)
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        CustomInputField(
            focusManager,
            value = description,
            label = "Opis časa",
            onValueChange = onDescriptionChange,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (userViewModel.loggedInUser!!.role == Role.TEACHER) {
            DropdownMenu(
                label = "Ucenik:",
                options = userViewModel.allStudents,
                selectedOption = studentId,
                onOptionSelected = { onStudentChange(it) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
