package ru.dmitriyt.streetplay.presentation.login

import com.arellomobile.mvp.InjectViewState
import ru.dmitriyt.streetplay.data.storage.Pref
import ru.dmitriyt.streetplay.domain.repository.IAuthRepository
import ru.dmitriyt.streetplay.presentation.global.BasePresenter
import javax.inject.Inject

@InjectViewState
class LoginPresenter @Inject constructor(private val authRepository: IAuthRepository, private val pref: Pref): BasePresenter<ILoginView>() {

    fun login(login: String, password: String) {
        if (login.isBlank()) {
            viewState.showEmptyLoginError()
            return
        }
        if (password.isBlank()) {
            viewState.showEmptyPasswordError()
            return
        }
        viewState.setLoading(true)
        authRepository.login(login, password).subscribe({
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