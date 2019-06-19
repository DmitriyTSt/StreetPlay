package ru.dmitriyt.streetplay.presentation.login

import com.arellomobile.mvp.InjectViewState
import ru.dmitriyt.streetplay.data.storage.Pref
import ru.dmitriyt.streetplay.domain.repository.IAuthRepository
import ru.dmitriyt.streetplay.presentation.global.BasePresenter

@InjectViewState
class LoginPresenter(private val authRepository: IAuthRepository, private val pref: Pref): BasePresenter<ILoginView>() {

    fun login(nickname: String) {
        if (nickname.isBlank()) {
            viewState.showEmptyNickname()
            return
        }
        authRepository.registration(nickname).subscribe({
            pref.userToken = it.token
            viewState.goToMap()
        },{
            viewState.showError(it.message)
        }).add()
    }
}