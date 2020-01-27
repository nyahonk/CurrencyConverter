package com.nyahonk.currencyconverter.di.modules

import com.nyahonk.currencyconverter.data.repository.ConversionRatesRepository
import com.nyahonk.currencyconverter.interactor.currencyConverter.CurrencyConverterInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CurrencyConverterModule {

    @Provides
    @Singleton
    fun provideCurrencyConverterInteractor(repository: ConversionRatesRepository):
            CurrencyConverterInteractor = CurrencyConverterInteractor(repository)
}