package ru.dmitriyt.streetplay.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.dmitriyt.streetplay.data.network.AuthApiService
import ru.dmitriyt.streetplay.data.network.PlaceApiService
import ru.dmitriyt.streetplay.data.storage.Pref
import javax.inject.Named
import javax.inject.Singleton

@Module
class RemoteModule {

    @Provides
    fun provideGson(): Gson =
        GsonBuilder().setLenient().create()

    @Provides
    @Named("auth")
    fun provideAuthOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    @Provides
    @Named("main")
    fun provideOkHttpClient(pref: Pref): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request()
                val token = pref.userToken
                if (token != null) {
                    it.proceed(request.newBuilder().addHeader("apikey", token).build())
                } else {
                    it.proceed(it.request())
                }
            }
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    @Provides
    @Named("auth")
    fun provideAuthRetrofit(gson: Gson, @Named("auth") okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://dmitriyt.profsoft.int:81/index.php/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Named("main")
    fun provideRetrofit(gson: Gson, @Named("main") okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://dmitriyt.profsoft.int:81/index.php/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideAuthApiService(@Named("auth") retrofit: Retrofit): AuthApiService = retrofit.create(AuthApiService::class.java)

    @Provides
    @Singleton
    fun providePlaceApiService(@Named("main") retrofit: Retrofit): PlaceApiService = retrofit.create(PlaceApiService::class.java)


}