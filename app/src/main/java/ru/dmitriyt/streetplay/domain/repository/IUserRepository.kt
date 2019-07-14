package ru.dmitriyt.streetplay.domain.repository

import io.reactivex.Completable

interface IUserRepository {
    fun setUserLogin(login: String, password: String): Completable
}