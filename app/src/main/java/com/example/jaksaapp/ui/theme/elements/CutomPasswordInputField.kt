
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.jaksaapp.ui.theme.BrownNavbar
import com.example.jaksaapp.ui.theme.Cream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomPasswordInputField(
    focusManager: FocusManager,
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    isPasswordVisible: Boolean,
    onPasswordVisibilityChange: () -> Unit,
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
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = BrownNavbar,
            cursorColor = BrownNavbar,
            containerColor = Cream,
            disabledBorderColor = BrownNavbar,
            unfocusedBorderColor = BrownNavbar,
            focusedTextColor = BrownNavbar,
            unfocusedTextColor = BrownNavbar
        ),
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (isPasswordVisible) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }
            val description = if (isPasswordVisible) "Hide password" else "Show password"

            // Toggle button to hide or display password
            IconButton(onClick = onPasswordVisibilityChange) {
                Icon(imageVector = image, description)
            }
        }

    )
}
