package ru.dmitriyt.streetplay.presentation.login

import com.arellomobile.mvp.MvpView

interface ILoginView: MvpView {
    fun goToMap()
    fun showError(message: String?)
    fun showEmptyNickname()
}