package ru.dmitriyt.streetplay.presentation.login

import com.arellomobile.mvp.InjectViewState
import ru.dmitriyt.streetplay.presentation.global.BasePresenter

@InjectViewState
class LoginPresenter: BasePresenter<ILoginView>() {

    fun login(nickname: String) {
        if (nickname.isBlank()) {
            viewState.showEmptyNickname()
            return
        }
        viewState.goToMap()
    }
}