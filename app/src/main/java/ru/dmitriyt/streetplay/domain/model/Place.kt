package ru.dmitriyt.streetplay.domain.model

import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName
import com.google.maps.android.clustering.ClusterItem
import java.util.*

class Place (
    val id: Int? = null,
    @SerializedName("type") val name: String? = null,
    val description: String? = null,
    val images: String? = null,
    val coords: Coords? = null,
    val messages: List<Message>? = null,
    val lastTime: Long? = null
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

    fun getLastOnline(): Long {
        return Calendar.getInstance().timeInMillis / 1000
    }

}