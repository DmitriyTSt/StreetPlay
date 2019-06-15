package ru.dmitriyt.streetplay.domain.model

class Place (
    val id: Int?,
    val title: String?,
    val description: String?,
    val images: List<String>?,
    val coords: Coords?,
    val messages: List<Message>?
)