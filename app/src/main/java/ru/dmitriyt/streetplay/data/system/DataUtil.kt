package ru.dmitriyt.streetplay.data.system

import java.text.SimpleDateFormat
import java.util.*

object DataUtil {
    fun getFormatTime(seconds: Long): String {
        val now = Calendar.getInstance()
        val time = Calendar.getInstance()
        time.timeInMillis = seconds * 1000
        if (time.get(Calendar.YEAR) == now.get(Calendar.YEAR)) {
            if (time.get(Calendar.DAY_OF_YEAR) == now.get(Calendar.DAY_OF_YEAR)) {
                return SimpleDateFormat("HH:mm", Locale.getDefault()).format(time.timeInMillis)
            }
            if (time.get(Calendar.DAY_OF_YEAR) == now.get(Calendar.DAY_OF_YEAR) - 1) {
                return "вчера"
            }
        }
        return SimpleDateFormat("dd.MM.YYYY", Locale.getDefault()).format(time.timeInMillis)
    }
}