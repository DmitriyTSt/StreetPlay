package ru.dmitriyt.streetplay.domain.model

import java.text.SimpleDateFormat
import java.util.*

class Message (
    val id: Int? = null,
    val placeId: Int,
    val text: String?,
    val createdAt: Long? = null,
    val author: User? = null
) {
    fun getChatFormatTime(): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = (createdAt?:0) * 1000
        val now = Calendar.getInstance()
        if (now.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)) {
            return SimpleDateFormat("HH:mm", Locale.getDefault()).format(calendar.time)
        }
        if (now.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH) + 1) {
            return "вчера"
        }
        if (now.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) {
            return SimpleDateFormat("dd MMM", Locale.getDefault()).format(calendar.time)
        }
        return SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(calendar.time)
    }

    fun getMessageFormatTime(): String {
        return SimpleDateFormat("HH:mm", Locale.getDefault()).format((createdAt?:0) * 1000)
    }
}