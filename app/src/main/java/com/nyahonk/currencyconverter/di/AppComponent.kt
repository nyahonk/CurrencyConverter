package com.nyahonk.currencyconverter.di

import com.nyahonk.currencyconverter.AppLauncher
import com.nyahonk.currencyconverter.data.network.ConversionRateRestApi
import com.nyahonk.currencyconverter.di.modules.*
import com.nyahonk.currencyconverter.presentation.currencyConverter.CurrencyConverterViewModel
import dagger.Component
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Component(modules = [
    AppModule::class,
    NavigationModule::class,
    RestApiModule::class,
    DatabaseModule::class,
    CurrencyConverterModule::class
])
@Singleton
interface AppComponent {

    fun appLauncher(): AppLauncher
    fun router(): Router
    fun navigatorHolder(): NavigatorHolder
    fun api(): ConversionRateRestApi
    fun currencyConverterViewModel(): CurrencyConverterViewModel
}