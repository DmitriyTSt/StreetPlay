package ru.dmitriyt.streetplay.data.network

import io.reactivex.Completable
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ru.dmitriyt.streetplay.domain.model.Message
import ru.dmitriyt.streetplay.domain.model.Place

interface PlaceApiService {
    @GET("places")
    fun getPlaces(): Flowable<List<Place>>

    @GET("places/{uuid}")
    fun getPlace(@Path("uuid") id: Int): Flowable<Place>

    @POST("send-message")
    fun sendMessage(@Body message: Message): Flowable<Message>
}