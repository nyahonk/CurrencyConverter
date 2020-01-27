package com.nyahonk.currencyconverter.data.persistance.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nyahonk.currencyconverter.data.persistance.db.dao.ConversionPairDao
import com.nyahonk.currencyconverter.data.persistance.db.dao.ExchangeRatesDao
import com.nyahonk.currencyconverter.entity.CurrencyValueEntity
import com.nyahonk.currencyconverter.entity.ExchangeRatesEntity

@Database(entities = [ExchangeRatesEntity::class, CurrencyValueEntity::class], version = 1)
@TypeConverters(ExchangeTypeConverter::class)
abstract class ConversionPairsDatabase : RoomDatabase() {

    abstract fun exchangeRatesDao(): ExchangeRatesDao
    abstract fun conversionPairDao(): ConversionPairDao

}