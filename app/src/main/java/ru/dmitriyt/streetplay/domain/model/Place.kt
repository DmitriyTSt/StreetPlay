package ru.dmitriyt.streetplay.domain.model

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

class Place (
    val id: Int?,
    val name: String?,
    val description: String?,
    val images: List<String>?,
    val coords: Coords?,
    val messages: List<Message>?
) : ClusterItem {
    override fun getSnippet(): String? {
        return null
    }

    override fun getTitle(): String? {
        return null
    }

    override fun getPosition(): LatLng {
        return LatLng(coords?.lat?:0.0, coords?.lng?:0.0)
    }

}