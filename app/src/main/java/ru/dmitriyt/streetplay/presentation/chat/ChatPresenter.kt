package ru.dmitriyt.streetplay.presentation.chat

import com.arellomobile.mvp.InjectViewState
import ru.dmitriyt.streetplay.domain.model.Place
import ru.dmitriyt.streetplay.domain.repository.IPlaceRepository
import ru.dmitriyt.streetplay.presentation.global.BasePresenter

@InjectViewState
class ChatPresenter(private val placeRepository: IPlaceRepository): BasePresenter<IChatView>() {

    private var place: Place? = null

    fun getPlace(placeId: Int) {
        viewState.setLoading(true)
        placeRepository.getPlace(placeId).subscribe({
            viewState.setLoading(false)
            this.place = it
            if (it.messages != null && it.messages.isNotEmpty()) {
                viewState.showMessages(it.messages.reversed())
            } else {
                viewState.showEmptyMessageList()
            }
            viewState.showChatTitle(place?.name?:"")
            viewState.showChatImage(place?.images)
        },{
            viewState.setLoading(false)
            viewState.showError(it.message)
        }).add()
    }

    fun sendMessage(text: String) {
        if (place == null) return

        placeRepository.sendMessage(place!!.id?:0, text).subscribe({
            viewState.showNewMessage(it)
        },{
            viewState.showError(it.message)
        }).add()
    }
}