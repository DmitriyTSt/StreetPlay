package ru.dmitriyt.streetplay.domain.repository

import io.reactivex.Flowable
import ru.dmitriyt.streetplay.domain.model.Message

interface IChatRepository {
    fun getMessages(placeId: Int): Flowable<List<Message>>
    fun sendMessage(placeId: Int, message: String)
}