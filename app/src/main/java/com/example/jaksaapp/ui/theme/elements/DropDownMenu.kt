package com.example.jaksaapp.ui.theme.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.jaksaapp.ui.theme.BrownNavbar
import com.example.jaksaapp.ui.theme.Chocolate
import com.example.jaksaapp.ui.theme.Cream
import androidx.compose.material3.*
import com.example.jaksaapp.remote.dto.UserDto
import com.example.jaksaapp.ui.theme.DarkBrown

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenu(
    label: String,
    options: List<UserDto>,
    selectedOption: Long,
    onOptionSelected: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(
            text = label,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = BrownNavbar
        )
        Spacer(modifier = Modifier.height(4.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                placeholder = { Text("Izaberi ucenika", color = BrownNavbar) },
                value = options.find { it.id == selectedOption }?.let { "${it.firstname} ${it.lastname}" } ?: "",
                onValueChange = {},
                readOnly = true,

                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                modifier = modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .background(Cream).padding(vertical = 4.dp, horizontal = 4.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = BrownNavbar,
                    cursorColor = BrownNavbar,
                    containerColor = Cream,
                    disabledBorderColor = BrownNavbar,
                    unfocusedBorderColor = BrownNavbar,
                    focusedTextColor = BrownNavbar,
                    unfocusedTextColor = BrownNavbar
                ),
                shape = RoundedCornerShape(10.dp),
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "${option.firstname} ${option.lastname}",
                                color = DarkBrown
                            )
                        },
                        onClick = {
                            onOptionSelected(option.id!!)
                            expanded = false
                        },
                        colors = MenuDefaults.itemColors(
                            textColor = DarkBrown,
                            disabledTextColor = BrownNavbar
                        ),
                        modifier = Modifier.background(Cream)
                    )
                }
            }
        }
    }
}

