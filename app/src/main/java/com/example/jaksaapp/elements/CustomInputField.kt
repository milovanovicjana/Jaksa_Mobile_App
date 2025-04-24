
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.unit.dp
import com.example.jaksaapp.ui.theme.BrownNavbar
import com.example.jaksaapp.ui.theme.Cream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomInputField(
    focusManager: FocusManager,
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    trailingIcon: @Composable (() -> Unit) = {},
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        label = { Text(text = label, color = BrownNavbar) },
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .padding(vertical = 4.dp, horizontal = 4.dp),
        keyboardActions = KeyboardActions(onDone = {
            focusManager.moveFocus(FocusDirection.Down)
        }),
        singleLine = true,
        trailingIcon = trailingIcon,
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = BrownNavbar,
            cursorColor = BrownNavbar,
            containerColor = Cream,
            disabledBorderColor = BrownNavbar,
            unfocusedBorderColor = BrownNavbar,
            focusedTextColor = BrownNavbar,
            unfocusedTextColor = BrownNavbar
        )

    )
}
