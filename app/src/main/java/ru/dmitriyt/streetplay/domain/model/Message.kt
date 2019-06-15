package ru.dmitriyt.streetplay.domain.model

class Message (
    val id: Int?,
    val place: Place?,
    val text: String?,
    val createdAt: Int?,
    val author: User?
)