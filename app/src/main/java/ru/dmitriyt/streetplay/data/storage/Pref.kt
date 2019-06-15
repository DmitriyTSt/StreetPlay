package ru.dmitriyt.streetplay.data.storage

import android.content.Context
import javax.inject.Inject

class Pref @Inject constructor(private val context: Context) {

    private fun getSharedPreferences(prefName: String) = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

    private val USER_DATA = "user_data"
    private val USER_UUID = ""
    private val userPrefs by lazy { getSharedPreferences(USER_DATA) }

    var userUuid: String?
        get() = userPrefs.getString(USER_UUID, null)
        set(value) {
            userPrefs.edit().putString(USER_UUID, value).apply()
        }
}