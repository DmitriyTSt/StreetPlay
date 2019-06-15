package ru.dmitriyt.streetplay.presentation.map

import com.arellomobile.mvp.MvpView
import ru.dmitriyt.streetplay.domain.model.Place

interface IMapView: MvpView {
    fun showPlaces(places: List<Place>)
}