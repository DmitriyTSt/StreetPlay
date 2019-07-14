package ru.dmitriyt.streetplay.di

import dagger.Module
import dagger.Provides
import ru.dmitriyt.streetplay.data.network.AuthApiService
import ru.dmitriyt.streetplay.data.network.PlaceApiService
import ru.dmitriyt.streetplay.data.network.UserApiService
import ru.dmitriyt.streetplay.data.repository.AuthRepository
import ru.dmitriyt.streetplay.data.repository.PlaceRepository
import ru.dmitriyt.streetplay.data.repository.UserRepository
import ru.dmitriyt.streetplay.domain.repository.IAuthRepository
import ru.dmitriyt.streetplay.domain.repository.IPlaceRepository
import ru.dmitriyt.streetplay.domain.repository.IUserRepository

@Module
class RepositoryModule {

    @Provides
    fun provideAuthRepository(authApiService: AuthApiService): IAuthRepository = AuthRepository(authApiService)

    @Provides
    fun providePlaceRepository(placeApiService: PlaceApiService): IPlaceRepository = PlaceRepository(placeApiService)

    @Provides
    fun provideUserRepository(userApiService: UserApiService): IUserRepository = UserRepository(userApiService)
}