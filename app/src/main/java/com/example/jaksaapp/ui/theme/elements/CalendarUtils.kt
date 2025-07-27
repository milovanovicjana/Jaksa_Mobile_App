import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun Date.formatToMonthString(): String = SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(this)
fun getWeekDays(startFromSunday: Boolean): MutableList<Int> {
    val lista = (1..7).toList()
    return (if (startFromSunday) lista else lista.drop(1) + lista.take(1)).toMutableList()
}
fun Date.formatToWeekDay(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.DAY_OF_WEEK)
}
fun Int.getDayOfWeek3Letters(): String? = Calendar.getInstance().apply {
    set(Calendar.DAY_OF_WEEK, this@getDayOfWeek3Letters)
}.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault())

fun Date.formatToCalendarDay(): String = SimpleDateFormat("d", Locale.getDefault()).format(this)

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

fun generateCalendarDays(monthDate: Date): MutableList<Pair<Date, Boolean>> {
    val calendar = Calendar.getInstance().apply {
        time = monthDate
        set(Calendar.DAY_OF_MONTH, 1)
    }

    val days = mutableListOf<Pair<Date, Boolean>>()
    val maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

    for (day in 1..maxDay) {
        calendar.set(Calendar.DAY_OF_MONTH, day)
        val date = calendar.time

        // Remove
        if (day == 15) {
            days.add(Pair(date, true))
        } else {
            days.add(Pair(date, false))
        }
    }

    return days
}
