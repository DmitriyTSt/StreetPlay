package ru.dmitriyt.streetplay.data.repository

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.dmitriyt.streetplay.data.model.LoginForm
import ru.dmitriyt.streetplay.data.network.AuthApiService
import ru.dmitriyt.streetplay.data.network.PlaceApiService
import ru.dmitriyt.streetplay.domain.model.Place
import ru.dmitriyt.streetplay.domain.model.TokenResponse
import ru.dmitriyt.streetplay.domain.model.User
import ru.dmitriyt.streetplay.domain.repository.IAuthRepository
import ru.dmitriyt.streetplay.domain.repository.IPlaceRepository

class AuthRepository(private val authApiService: AuthApiService): IAuthRepository {
    override fun registration(nickname: String): Flowable<TokenResponse> {
        return authApiService.registration(User(nickname = nickname))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun login(login: String, password: String): Flowable<TokenResponse> {
        return authApiService.login(LoginForm(login, password))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}