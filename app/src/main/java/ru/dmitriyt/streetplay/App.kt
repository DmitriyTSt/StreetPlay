package ru.dmitriyt.streetplay

import android.app.Application
import ru.dmitriyt.streetplay.di.AppComponent
import ru.dmitriyt.streetplay.di.AppModule
import ru.dmitriyt.streetplay.di.DaggerAppComponent

class App: Application() {

    companion object {
        lateinit var INSTANCE: App
            private set
        const val BASE_URL = "http://dmitriyt.profsoft.online/index.php/api/"
    }

    val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        appComponent.inject(this)
    }
}