package ru.dmitriyt.streetplay.di

import dagger.Component
import ru.dmitriyt.streetplay.App
import ru.dmitriyt.streetplay.ui.map.MapsActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, PresenterModule::class, RemoteModule::class, RepositoryModule::class])
interface AppComponent {
    fun inject(application: App)

    fun inject(activity: MapsActivity)
}