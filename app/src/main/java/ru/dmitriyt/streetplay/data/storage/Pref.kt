package ru.dmitriyt.streetplay.data.storage

import android.content.Context
import javax.inject.Inject

class Pref @Inject constructor(private val context: Context) {

    private fun getSharedPreferences(prefName: String) = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

    private val USER_DATA = "user_data"
    private val USER_UUID = "user_uuid"
    private val USER_TOKEN = "user_token"
    private val userPrefs by lazy { getSharedPreferences(USER_DATA) }

    var userUuid: String?
        get() = userPrefs.getString(USER_UUID, null)
        set(value) {
            userPrefs.edit().putString(USER_UUID, value).apply()
        }

    var userToken: String?
        get() = userPrefs.getString(USER_TOKEN, null)
        set(value) {
            userPrefs.edit().putString(USER_TOKEN, value).apply()
        }
}