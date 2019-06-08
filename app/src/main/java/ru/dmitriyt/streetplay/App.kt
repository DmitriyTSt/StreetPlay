package ru.dmitriyt.streetplay

import android.app.Application
import ru.dmitriyt.streetplay.di.AppComponent
import ru.dmitriyt.streetplay.di.AppModule

class App: Application() {

    companion object {
        lateinit var INSTANCE: App
            private set
    }

    val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerAppComponent
            .builder()
            .applicationModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        appComponent.inject(this)
    }
}