package ru.dmitriyt.streetplay.domain.model

import com.google.gson.annotations.SerializedName

class Coords (
    @SerializedName("ltd") val lat: Double,
    val lng: Double
)