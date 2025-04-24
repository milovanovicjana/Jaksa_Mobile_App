
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomText(
    text: String,
    fontSize: TextUnit = 19.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    textAlign: TextAlign = TextAlign.Justify,
    padding: Dp = 20.dp,
    color: Color = Color.Black,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = fontSize,
            fontWeight = fontWeight,
            textAlign = textAlign
        ),
        color = color,
        modifier = modifier.padding(padding)
    )
}
