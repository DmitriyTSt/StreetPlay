package ru.dmitriyt.streetplay.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.dmitriyt.streetplay.App
import javax.inject.Singleton

@Module
class AppModule(private val application: App) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application
}