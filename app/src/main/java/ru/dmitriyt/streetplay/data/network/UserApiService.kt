package ru.dmitriyt.streetplay.data.network

import io.reactivex.Completable
import retrofit2.http.Body
import retrofit2.http.PATCH
import ru.dmitriyt.streetplay.data.model.LoginForm

interface UserApiService {
    @PATCH("users/registration")
    fun setUserLogin(@Body loginForm: LoginForm): Completable
}