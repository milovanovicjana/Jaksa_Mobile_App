package com.example.jaksaapp.ui.theme.elements

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jaksaapp.ui.theme.BrownButton
import com.example.jaksaapp.ui.theme.BrownNavbar
import com.example.jaksaapp.ui.theme.Chocolate

@Composable
fun RequestsSelector(selectedTabIndex: Int, onSelectedTab: (Int) -> Unit, titles: List<String>) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        contentColor = Chocolate,
        containerColor = BrownButton,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier
                    .tabIndicatorOffset(tabPositions[selectedTabIndex])
                    .height(3.dp),
                color = Chocolate
            )
        },
        modifier = Modifier.clip(RoundedCornerShape(20.dp))
    ) {
        titles.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { onSelectedTab(index) },
                text = {
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        fontWeight = if (selectedTabIndex == index) FontWeight.ExtraBold else FontWeight.Bold,
                        color = if (selectedTabIndex == index) Chocolate else BrownNavbar
                    )
                }
            )
        }
    }
}
