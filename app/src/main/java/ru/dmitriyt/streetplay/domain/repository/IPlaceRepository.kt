package ru.dmitriyt.streetplay.domain.repository

import io.reactivex.Flowable
import ru.dmitriyt.streetplay.domain.model.Place

interface IPlaceRepository {
    fun getPlaces(): Flowable<List<Place>>
    fun getPlace(placeId: Int): Flowable<Place>
}