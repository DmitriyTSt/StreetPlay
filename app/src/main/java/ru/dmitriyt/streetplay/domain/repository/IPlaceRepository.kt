package ru.dmitriyt.streetplay.domain.repository

import io.reactivex.Flowable
import ru.dmitriyt.streetplay.domain.model.Message
import ru.dmitriyt.streetplay.domain.model.Place

interface IPlaceRepository {
    fun getPlaces(): Flowable<List<Place>>
    fun getPlace(placeId: Int): Flowable<Place>
    fun sendMessage(placeId: Int, text: String): Flowable<Message>
    fun addPlace(place: Place): Flowable<Place>
    fun uploadImage(path: String): Flowable<String>
}