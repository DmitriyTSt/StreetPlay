package ru.dmitriyt.streetplay.domain.model

import com.google.gson.annotations.SerializedName

class User (
    @SerializedName("id") val uuid: String? = null,
    val nickname: String?
)