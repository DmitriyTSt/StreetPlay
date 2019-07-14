package ru.dmitriyt.streetplay.di

import dagger.Component
import ru.dmitriyt.streetplay.App
import ru.dmitriyt.streetplay.ui.chat.ChatActivity
import ru.dmitriyt.streetplay.ui.login.AuthActivity
import ru.dmitriyt.streetplay.ui.login.LoginFragment
import ru.dmitriyt.streetplay.ui.login.RegistrationFragment
import ru.dmitriyt.streetplay.ui.map.MapsActivity
import ru.dmitriyt.streetplay.ui.place.PlaceCreateActivity
import ru.dmitriyt.streetplay.ui.settings.SettingsActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RemoteModule::class, RepositoryModule::class, PreferenceModule::class])
interface AppComponent {
    fun inject(application: App)

    fun inject(activity: MapsActivity)
    fun inject(fragment: RegistrationFragment)
    fun inject(fragment: LoginFragment)
    fun inject(activity: ChatActivity)
    fun inject(activity: PlaceCreateActivity)
    fun inject(activity: SettingsActivity)
    fun inject(activity: AuthActivity)
}