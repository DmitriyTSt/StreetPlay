package ru.dmitriyt.streetplay.presentation.place

import com.arellomobile.mvp.InjectViewState
import ru.dmitriyt.streetplay.data.repository.PlaceRepository
import ru.dmitriyt.streetplay.domain.model.Place
import ru.dmitriyt.streetplay.domain.repository.IPlaceRepository
import ru.dmitriyt.streetplay.presentation.global.BasePresenter
import javax.inject.Inject

@InjectViewState
class PlaceCreatePresenter @Inject constructor(private val placeRepository: IPlaceRepository): BasePresenter<IPlaceCreateView>() {
    private var imageUrl: String? = null

    fun uploadImage(path: String) {
        viewState.setLoading(true)
        placeRepository.uploadImage(path).subscribe({
            viewState.apply {
                setLoading(false)
                showImage(path)
            }
            imageUrl = it
        },{
            viewState.apply {
                setLoading(false)
                showMessage(it.message?:"Неизвестная ошибка")
            }
        }).add()
    }

    fun savePlace(title: String, description: String) {
        if (title == "") {
            viewState.showTitleError("Заполните обязательное поле")
            return
        }
        viewState.setLoading(true)
        placeRepository.addPlace(Place(name = title, description = description, images = imageUrl))
            .subscribe({
                viewState.apply {
                    setLoading(false)
                    successCreate()
                }
            },{
                viewState.apply {
                    setLoading(false)
                    showMessage(it.message?:"Неизвестная ошибка")
                }
            }).add()
    }
}