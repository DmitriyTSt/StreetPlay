package ru.dmitriyt.streetplay.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.dmitriyt.streetplay.data.storage.Pref

@Module
class PreferenceModule {
    @Provides
    fun providePref(context: Context): Pref = Pref(context)
}