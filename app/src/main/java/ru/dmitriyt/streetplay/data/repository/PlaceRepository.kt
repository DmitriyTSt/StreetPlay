package ru.dmitriyt.streetplay.data.repository

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import ru.dmitriyt.streetplay.data.network.PlaceApiService
import ru.dmitriyt.streetplay.domain.model.Message
import ru.dmitriyt.streetplay.domain.model.Place
import ru.dmitriyt.streetplay.domain.repository.IPlaceRepository
import java.io.File

class PlaceRepository(private val placeApiService: PlaceApiService): IPlaceRepository {
    override fun getPlaces(): Flowable<List<Place>> {
        return placeApiService.getPlaces()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getPlace(placeId: Int): Flowable<Place> {
        return placeApiService.getPlace(placeId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun sendMessage(placeId: Int, text: String): Flowable<Message> {
        return placeApiService.sendMessage(Message(placeId = placeId, text = text))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun addPlace(place: Place): Flowable<Place> {
        return placeApiService.addPlace(place)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun uploadImage(path: String): Flowable<String> {
        val file = File(path)
        val filePart = MultipartBody.Part.createFormData(
            "image", file.name, RequestBody.create(MediaType.parse("image/*"), file))
        return placeApiService.uploadImage(filePart)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}