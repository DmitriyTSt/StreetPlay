package ru.dmitriyt.streetplay.ui.chat

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_chat.*
import ru.dmitriyt.streetplay.App
import ru.dmitriyt.streetplay.R
import ru.dmitriyt.streetplay.data.storage.Pref
import ru.dmitriyt.streetplay.domain.model.Message
import ru.dmitriyt.streetplay.presentation.chat.ChatPresenter
import ru.dmitriyt.streetplay.presentation.chat.IChatView
import ru.dmitriyt.streetplay.presentation.login.LoginPresenter
import ru.dmitriyt.streetplay.ui.global.BaseActivity
import javax.inject.Inject

class ChatActivity: BaseActivity(), IChatView {
    override val layoutRes: Int = R.layout.activity_chat
    private lateinit var adapter: MessageAdapter

    init {
        App.INSTANCE.appComponent.inject(this@ChatActivity)
    }

    @Inject
    lateinit var pref: Pref

    @Inject
    @InjectPresenter
    lateinit var presenter: ChatPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutManager = LinearLayoutManager(this)
        //layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        message_list.layoutManager = layoutManager
        adapter =  MessageAdapter(ArrayList(), pref.userUuid)
        message_list.adapter = adapter
        chat_img.setOnClickListener {

        }
        btn_back.setOnClickListener {
            finish()
        }
        btn_send.setOnClickListener {
            if (new_message_text.text.toString().isNotBlank()) {
                presenter.sendMessage(new_message_text.text.toString())
            }
            new_message_text.setText("")
        }

        presenter.getPlace(intent.getIntExtra("placeId", 0))
    }

    override fun showMessages(messages: List<Message>) {
        empty_list_text.visibility = View.GONE
        message_list.visibility = View.VISIBLE
        adapter.setMessages(messages)
    }

    override fun showNewMessage(message: Message) {
        adapter.addMessage(message)
        message_list.scrollToPosition(adapter.getLastPosition())
    }

    override fun showEmptyMessageList() {
        empty_list_text.visibility = View.VISIBLE
        message_list.visibility = View.GONE
    }

    override fun showChatTitle(title: String) {
        chat_title.text = title
    }

    override fun showChatImage(imageUrl: String?) {
        Glide.with(this)
            .load(imageUrl)
            .into(chat_img)
    }

    override fun setLoading(isLoading: Boolean) {
        progress_bar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun showError(message: String?) {
        Snackbar.make(message_list, message?:"Неизвестная ошибка, попробуйте позже", Snackbar.LENGTH_SHORT).show()
    }
}