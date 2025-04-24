package com.example.jaksaapp.elements

import DropdownMenuTopBar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jaksaapp.R
import com.example.jaksaapp.ui.theme.BrownNavbar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar(isLoggedIn: Boolean, navHostController: NavHostController) {
    val expanded = remember { mutableStateOf(false) }
    val context = LocalContext.current

    val appName = context.getString(R.string.app_name)
    val phoneNumber = context.getString(R.string.phone_number)

    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = appName,
                    style = TextStyle(
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    imageVector = Icons.Filled.Phone,
                    contentDescription = "Phone Icon",
                    modifier = Modifier.size(22.dp)
                        .clickable {
                            // Make a phone call using intent
                        }
                )
                Text(
                    phoneNumber,
                    style = TextStyle(
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        },
        actions = {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "Dropdown Menu",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clickable { expanded.value = true },
                tint = Color.White
            )
            DropdownMenuTopBar(expanded, isLoggedIn, navHostController)
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = BrownNavbar,
            titleContentColor = Color.White
        ),
        modifier = Modifier.fillMaxWidth()
    )
}
