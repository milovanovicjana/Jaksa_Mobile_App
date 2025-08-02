import android.os.Build
import androidx.annotation.RequiresApi
import com.example.jaksaapp.remote.dto.ClassDto
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
val timeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")

@RequiresApi(Build.VERSION_CODES.O)
val timeParser: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")

@RequiresApi(Build.VERSION_CODES.O)
val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

fun Date.formatToMonthString(): String = SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(this)
fun Date.formatToDateStringDisplay(): String = SimpleDateFormat("dd.MM.yyyy.", Locale.getDefault()).format(this)
fun Date.formatToDateString(): String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(this)
fun Date.formatToWeekDay(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.DAY_OF_WEEK)
}
fun Date.formatToCalendarDay(): String = SimpleDateFormat("d", Locale.getDefault()).format(this)
fun Int.getDayOfWeek3Letters(): String? = Calendar.getInstance().apply {
    set(Calendar.DAY_OF_WEEK, this@getDayOfWeek3Letters)
}.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault())

fun dateParser(dateString: String): Date {
    return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(dateString)
}

fun getWeekDays(startFromSunday: Boolean): MutableList<Int> {
    val lista = (1..7).toList()
    return (if (startFromSunday) lista else lista.drop(1) + lista.take(1)).toMutableList()
}

fun getFirstDayOfMonth(date: Date): Date {
    return Calendar.getInstance().apply {
        time = date
        set(Calendar.DAY_OF_MONTH, 1)
    }.time
}

fun getNextMonth(date: Date): Date {
    return Calendar.getInstance().apply {
        time = date
        add(Calendar.MONTH, 1)
    }.time
}

fun getPreviousMonth(date: Date): Date {
    return Calendar.getInstance().apply {
        time = date
        add(Calendar.MONTH, -1)
    }.time
}

fun getYear(date: Date): Int {
    val calendar = Calendar.getInstance()
    calendar.time = date
    return calendar.get(Calendar.YEAR)
}

fun getMonth(date: Date): Int {
    val calendar = Calendar.getInstance()
    calendar.time = date
    return calendar.get(Calendar.MONTH) + 1
}

fun generateCalendarDays(monthDate: Date, classes: List<ClassDto>): MutableList<Pair<Date, Boolean>> {
    val calendar = Calendar.getInstance().apply {
        time = monthDate
        set(Calendar.DAY_OF_MONTH, 1)
    }

    val days = mutableListOf<Pair<Date, Boolean>>()
    val maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

    for (day in 1..maxDay) {
        calendar.set(Calendar.DAY_OF_MONTH, day)
        val date = calendar.time

        val hasClass = classes.any { classDto ->
            val classDate = dateParser(classDto.date)
            isSameDay(classDate, date)
        }

        days.add(Pair(date, hasClass))
    }

    return days
}

fun isSameDay(date1: Date, date2: Date): Boolean {
    val cal1 = Calendar.getInstance().apply { time = date1 }
    val cal2 = Calendar.getInstance().apply { time = date2 }
    return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
        cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
        cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH)
}
