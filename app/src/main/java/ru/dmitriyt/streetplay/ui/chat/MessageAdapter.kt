package ru.dmitriyt.streetplay.ui.chat

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.item_message.view.*
import ru.dmitriyt.streetplay.R
import ru.dmitriyt.streetplay.domain.model.Message

class MessageAdapter(
    private var messages: MutableList<Message>, private val uuid: String?
) : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_message, p0, false))
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        if (messages[position].author?.uuid == uuid) {
            params.addRule(RelativeLayout.ALIGN_PARENT_END)
        } else {
            params.addRule(RelativeLayout.ALIGN_PARENT_START)
        }
        holder.itemView.message_wrap.layoutParams = params
        holder.itemView.message_text.text = messages[position].text
        holder.itemView.message_time.text = messages[position].getMessageFormatTime()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun addMessage(message: Message) {
        messages.add(message)
        notifyDataSetChanged()
    }

    fun setMessages(messages: List<Message>) {
        this.messages.clear()
        this.messages.addAll(messages)
        notifyDataSetChanged()
    }

    fun getLastPosition(): Int {
        return messages.size - 1
    }
}