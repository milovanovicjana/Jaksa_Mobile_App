package com.example.jaksaapp.ui.theme.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jaksaapp.remote.dto.ClassStatus
import com.example.jaksaapp.ui.theme.BrownButton
import com.example.jaksaapp.ui.theme.BrownNavbar

fun mapClassStatusToString(status: ClassStatus): String = when (status) {
    ClassStatus.PENDING -> "NA CEKANJU"
    ClassStatus.REJECTED -> "ODBIJENI"
    ClassStatus.APPROVED -> "ODOBRENI"
}

@Composable
fun RequestFilters(statuses: List<ClassStatus>, selectedStatus: ClassStatus, onStatusSelect: (ClassStatus) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier.padding(horizontal = 5.dp)
    ) {
        statuses.forEach { status ->
            FilterChip(
                selected = selectedStatus == status,
                onClick = { onStatusSelect(status) },
                label = { Text(mapClassStatusToString(status)) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = BrownButton,
                    selectedLabelColor = BrownNavbar
                ),
                shape = RoundedCornerShape(20.dp)
            )
        }
    }
}
