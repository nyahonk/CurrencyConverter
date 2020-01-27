package com.nyahonk.currencyconverter

import android.app.Application
import com.nyahonk.currencyconverter.di.DaggerAppComponent
import com.nyahonk.currencyconverter.di.*
import com.nyahonk.currencyconverter.di.modules.*

class App : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .navigationModule(NavigationModule())
            .restApiModule(RestApiModule())
            .databaseModule(DatabaseModule())
            .currencyConverterModule(CurrencyConverterModule())
            .build()
    }
}