package ru.dmitriyt.streetplay.presentation.chat

import com.arellomobile.mvp.MvpView
import ru.dmitriyt.streetplay.domain.model.Message

interface IChatView: MvpView {
    fun showMessages(messages: List<Message>)
    fun showEmptyMessageList()
    fun showChatTitle(title: String)
    fun showChatImage(imageUrl: String?)
    fun showNewMessage(message: Message)
    fun setLoading(isLoading: Boolean)
    fun showError(message: String?)
}