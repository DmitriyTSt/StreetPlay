package ru.dmitriyt.streetplay.presentation.map

import com.arellomobile.mvp.InjectViewState
import ru.dmitriyt.streetplay.domain.model.Coords
import ru.dmitriyt.streetplay.domain.model.Place
import ru.dmitriyt.streetplay.presentation.global.BasePresenter

@InjectViewState
class MapPresenter: BasePresenter<IMapView>() {

    fun getPlaces() {
        val places = ArrayList<Place>()
        places.add(Place(1, "Футбол", "Площадка футбольная, покрытие хорошее", listOf(), Coords(51.536744, 46.001301), null))
        viewState.showPlaces(places)
    }
}