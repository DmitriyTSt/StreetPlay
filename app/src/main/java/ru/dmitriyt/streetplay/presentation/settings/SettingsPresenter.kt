package ru.dmitriyt.streetplay.presentation.settings

import com.arellomobile.mvp.InjectViewState
import ru.dmitriyt.streetplay.data.storage.Pref
import ru.dmitriyt.streetplay.domain.repository.IUserRepository
import ru.dmitriyt.streetplay.presentation.global.BasePresenter
import javax.inject.Inject

@InjectViewState
class SettingsPresenter @Inject constructor(private val userRepository: IUserRepository, private val pref: Pref): BasePresenter<ISettingsView>() {

    fun setLogin(login: String, password: String) {
        if (login == "") {
            viewState.showLoginError("Логин не должен быть пустым")
            return
        }
        if (password == "") {
            viewState.showPasswordError("Пароль не должен быть пустым")
            return
        }
        viewState.setLoading(true)
        userRepository.setUserLogin(login, password).subscribe({
            viewState.apply {
                setLoading(false)
                successSetLogin()
            }
        },{
            viewState.apply {
                setLoading(false)
                showError(it.message?:"Неизвестная ошибка")
            }
        }).add()
    }

    fun logout() {
        pref.userToken = null
        pref.userUuid = null
        viewState.successLogout()
    }
}