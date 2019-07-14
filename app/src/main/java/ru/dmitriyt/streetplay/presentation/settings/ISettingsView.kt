package ru.dmitriyt.streetplay.presentation.settings

import com.arellomobile.mvp.MvpView

interface ISettingsView: MvpView {
    fun successSetLogin()
    fun successLogout()
    fun setLoading(isLoading: Boolean)
    fun showError(msg: String)
    fun showLoginError(msg: String)
    fun showPasswordError(msg: String)
}