package com.nyahonk.currencyconverter.di.modules

import android.content.Context
import androidx.room.Room
import com.nyahonk.currencyconverter.data.persistance.db.ConversionPairsDatabase
import com.nyahonk.currencyconverter.data.persistance.db.dao.ConversionPairDao
import com.nyahonk.currencyconverter.data.persistance.db.dao.ExchangeRatesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): ConversionPairsDatabase =
        Room.databaseBuilder(context.applicationContext,
            ConversionPairsDatabase::class.java, "Sample.db")
            .build()
    
    @Provides
    @Singleton
    fun provideDatabaseDao(database: ConversionPairsDatabase): ConversionPairDao =
        database.conversionPairDao()

    @Provides
    @Singleton
    fun provideExchangeRatesDao(database: ConversionPairsDatabase): ExchangeRatesDao =
        database.exchangeRatesDao()
}