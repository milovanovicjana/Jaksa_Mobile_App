
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.example.jaksaapp.ui.theme.BrownNavbar

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    containerColor: Color = BrownNavbar,
    textColor: Color = Color.White,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier, // Modifier.align(Alignment.CenterHorizontally),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor
        ),
        border = BorderStroke(
            1.dp,
            SolidColor(BrownNavbar)
        )
    ) {
        CustomText(text = text, padding = 10.dp, color = textColor)
    }
}
