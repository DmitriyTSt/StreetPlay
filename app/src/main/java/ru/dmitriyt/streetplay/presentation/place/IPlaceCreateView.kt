package ru.dmitriyt.streetplay.presentation.place

import com.arellomobile.mvp.MvpView

interface IPlaceCreateView: MvpView {
    fun successCreate()
    fun setLoading(isLoading: Boolean)
    fun showImage(path: String)
    fun showMessage(msg: String)
    fun showTitleError(error: String)
}