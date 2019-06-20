package ru.dmitriyt.streetplay.domain.model

import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName
import com.google.maps.android.clustering.ClusterItem
import java.util.*

class Place (
    val id: Int?,
    @SerializedName("type") val name: String?,
    val description: String?,
    val images: String?,
    val coords: Coords?,
    val messages: List<Message>?,
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