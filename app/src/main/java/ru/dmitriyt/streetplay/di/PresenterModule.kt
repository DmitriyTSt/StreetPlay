package ru.dmitriyt.streetplay.di

import dagger.Module
import dagger.Provides
import ru.dmitriyt.streetplay.presentation.login.LoginPresenter
import ru.dmitriyt.streetplay.presentation.map.MapPresenter

@Module
class PresenterModule {
    @Provides
    fun provideMapPresenter() = MapPresenter()

    @Provides
    fun provideLoginPresenter() = LoginPresenter()
}