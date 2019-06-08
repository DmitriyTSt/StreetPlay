package ru.dmitriyt.streetplay.di

import dagger.Component
import ru.dmitriyt.streetplay.App
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(application: App)
}