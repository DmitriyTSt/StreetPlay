package ru.dmitriyt.streetplay.data.repository

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.dmitriyt.streetplay.data.model.LoginForm
import ru.dmitriyt.streetplay.data.network.UserApiService
import ru.dmitriyt.streetplay.domain.repository.IUserRepository

class UserRepository(private val userApiService: UserApiService): IUserRepository {
    override fun setUserLogin(login: String, password: String): Completable {
        return userApiService.setUserLogin(LoginForm(login, password))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}