package ru.dmitriyt.streetplay.presentation.login

import com.arellomobile.mvp.MvpView

interface ILoginView: MvpView {
    fun showEmptyLoginError()
    fun showEmptyPasswordError()
    fun setLoading(isLoading: Boolean)
    fun goToMap()
    fun showError(message: String?)
}