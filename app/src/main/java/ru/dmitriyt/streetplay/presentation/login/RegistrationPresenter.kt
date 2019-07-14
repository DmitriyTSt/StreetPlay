package ru.dmitriyt.streetplay.presentation.login

import com.arellomobile.mvp.InjectViewState
import ru.dmitriyt.streetplay.data.storage.Pref
import ru.dmitriyt.streetplay.domain.repository.IAuthRepository
import ru.dmitriyt.streetplay.presentation.global.BasePresenter
import javax.inject.Inject

@InjectViewState
class RegistrationPresenter @Inject constructor(private val authRepository: IAuthRepository, private val pref: Pref): BasePresenter<IRegistrationView>() {

    fun login(nickname: String) {
        if (nickname.isBlank()) {
            viewState.showEmptyNickname()
            return
        }
        viewState.setLoading(true)
        authRepository.registration(nickname).subscribe({
            pref.userToken = it.token
            pref.userUuid = it.uuid
            viewState.apply {
                setLoading(false)
                goToMap()
            }
        },{
            viewState.apply {
                setLoading(false)
                showError(it.message)
            }
        }).add()
    }
}