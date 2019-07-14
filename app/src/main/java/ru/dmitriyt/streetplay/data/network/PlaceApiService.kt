package ru.dmitriyt.streetplay.data.network

import io.reactivex.Completable
import io.reactivex.Flowable
import okhttp3.MultipartBody
import retrofit2.http.*
import ru.dmitriyt.streetplay.domain.model.Message
import ru.dmitriyt.streetplay.domain.model.Place

interface PlaceApiService {
    @GET("places")
    fun getPlaces(): Flowable<List<Place>>

    @GET("places/{id}")
    fun getPlace(@Path("id") id: Int): Flowable<Place>

    @POST("places")
    fun addPlace(@Body place: Place): Flowable<Place>

    @POST("send-message")
    fun sendMessage(@Body message: Message): Flowable<Message>

    @Multipart
    @POST("image/upload")
    fun uploadImage(@Part filePart: MultipartBody.Part): Flowable<String>
}