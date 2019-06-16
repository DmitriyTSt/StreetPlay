package ru.dmitriyt.streetplay.domain.repository

import io.reactivex.Flowable
import ru.dmitriyt.streetplay.domain.model.TokenResponse
import ru.dmitriyt.streetplay.domain.model.User

interface IAuthRepository {
    fun registration(nickname: String): Flowable<TokenResponse>
}