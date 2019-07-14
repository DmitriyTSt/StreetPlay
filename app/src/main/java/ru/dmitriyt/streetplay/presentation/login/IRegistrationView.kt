package ru.dmitriyt.streetplay.presentation.login

import com.arellomobile.mvp.MvpView

interface IRegistrationView: MvpView {
    fun goToMap()
    fun showError(message: String?)
    fun setLoading(isLoading: Boolean)
    fun showEmptyNickname()
}