package ru.dmitriyt.streetplay.di

import dagger.Module
import dagger.Provides
import ru.dmitriyt.streetplay.data.repository.AuthRepository
import ru.dmitriyt.streetplay.data.storage.Pref
import ru.dmitriyt.streetplay.domain.repository.IAuthRepository
import ru.dmitriyt.streetplay.presentation.login.LoginPresenter
import ru.dmitriyt.streetplay.presentation.map.MapPresenter

@Module
class PresenterModule {
    @Provides
    fun provideMapPresenter() = MapPresenter()

    @Provides
    fun provideLoginPresenter(authRepository: IAuthRepository, pref: Pref) = LoginPresenter(authRepository, pref)
}