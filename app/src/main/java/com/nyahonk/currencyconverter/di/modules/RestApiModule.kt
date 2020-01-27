package com.nyahonk.currencyconverter.di.modules

import com.google.gson.Gson
import com.nyahonk.currencyconverter.BuildConfig
import com.nyahonk.currencyconverter.data.network.ConversionRateRestApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RestApiModule {

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) builder.addNetworkInterceptor(HttpLoggingInterceptor()
            .apply {
                level = HttpLoggingInterceptor.Level.BODY
            })

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideCurrencyRateApi(okHttpClient: OkHttpClient, gson: Gson): ConversionRateRestApi =
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL + "/")
            .build()
            .create(ConversionRateRestApi::class.java)

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()
}
