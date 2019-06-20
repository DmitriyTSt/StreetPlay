package ru.dmitriyt.streetplay.presentation.map

import com.arellomobile.mvp.InjectViewState
import ru.dmitriyt.streetplay.domain.model.Coords
import ru.dmitriyt.streetplay.domain.model.Place
import ru.dmitriyt.streetplay.domain.repository.IPlaceRepository
import ru.dmitriyt.streetplay.presentation.global.BasePresenter

@InjectViewState
class MapPresenter(private val placeRepository: IPlaceRepository): BasePresenter<IMapView>() {

    fun getPlaces() {
        placeRepository.getPlaces().subscribe({
            viewState.showPlaces(it)
        },{
            viewState.showError(it.message)
        }).add()
//        val places = ArrayList<Place>()
//        places.add(Place(1, "Футбол", "Площадка футбольная, покрытие хорошее", null, Coords(51.536744, 46.001301), null))
//        places.add(Place(2, "Футбол", "Площадка футбольная, покрытие хорошее", "http://saysport.info/photos/thumbnails/place_22326_1.jpg", Coords(51.550795, 46.007599), null))
//        places.add(Place(3, "Теннис", "Площадка теннисная, покрытие хорошее", null, Coords(51.516744, 46.021301), null))
//        viewState.showPlaces(places)
    }
}