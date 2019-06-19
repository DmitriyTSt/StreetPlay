package ru.dmitriyt.streetplay.di

import dagger.Module
import dagger.Provides
import ru.dmitriyt.streetplay.data.network.AuthApiService
import ru.dmitriyt.streetplay.data.network.PlaceApiService
import ru.dmitriyt.streetplay.data.repository.AuthRepository
import ru.dmitriyt.streetplay.data.repository.PlaceRepository
import ru.dmitriyt.streetplay.domain.repository.IAuthRepository
import ru.dmitriyt.streetplay.domain.repository.IPlaceRepository

@Module
class RepositoryModule {

    @Provides
    fun provideAuthRepository(authApiService: AuthApiService): IAuthRepository = AuthRepository(authApiService)

    @Provides
    fun providePlaceRepository(placeApiService: PlaceApiService): IPlaceRepository = PlaceRepository(placeApiService)
}