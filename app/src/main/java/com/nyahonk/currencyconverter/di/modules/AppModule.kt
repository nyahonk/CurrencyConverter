package com.nyahonk.currencyconverter.di.modules

import android.content.Context
import com.nyahonk.currencyconverter.data.repository.ConversionRatesRepository
import com.nyahonk.currencyconverter.data.persistance.DatabaseUpdater
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideContext(): Context = context

    @Provides
    @Singleton
    fun provideDatabaseUpdater(repository: ConversionRatesRepository): DatabaseUpdater
            = DatabaseUpdater(repository)
}