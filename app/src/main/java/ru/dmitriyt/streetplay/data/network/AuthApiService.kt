package ru.dmitriyt.streetplay.data.network

import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.POST
import ru.dmitriyt.streetplay.domain.model.TokenResponse
import ru.dmitriyt.streetplay.domain.model.User

interface AuthApiService {
    @POST("registartion")
    fun registration(@Body user: User):Flowable<TokenResponse>


}