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
        places.add(Place(2, "Футбол", "Площадка футбольная, покрытие хорошее", listOf(
            "http://saysport.info/photos/thumbnails/place_22326_1.jpg",
            "http://saysport.info/photos/thumbnails/place_22326_2.jpg"
        ), Coords(51.550795, 46.007599), null))
        places.add(Place(3, "Теннис", "Площадка теннисная, покрытие хорошее", listOf(), Coords(51.516744, 46.021301), null))
        viewState.showPlaces(places)
    }
}