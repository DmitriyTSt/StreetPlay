package ru.dmitriyt.streetplay.data.network

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import ru.dmitriyt.streetplay.domain.model.Place

interface PlaceApiService {
    @GET("places")
    fun getPlaces(): Flowable<List<Place>>

    @GET("places/{id}")
    fun getPlace(@Path("id") id: Int): Flowable<Place>
}