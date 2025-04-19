import android.graphics.drawable.Icon
import androidx.compose.ui.graphics.Color
import org.w3c.dom.Text

data class StatisticElement(
    val icon: Icon,
    val value: Text,
    val label: Text,
    val backgroundColor: Color
)
