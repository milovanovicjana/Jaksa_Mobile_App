import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BulletList(items: List<String>) {
    repeat(items.size) { index ->
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(5.dp)) {
            Text(text = "\u2022 \t\t")
            Text(
                text = items[index],
                style = TextStyle(
                    fontSize = 15.sp,
                    textAlign = TextAlign.Justify

                ),
                color = Color.Black
            )
        }
    }
}
