package com.nyahonk.currencyconverter.data.persistance.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nyahonk.currencyconverter.CurrenciesMap
import com.nyahonk.currencyconverter.entity.ExchangeRatesEntity
import io.reactivex.Single

@Dao
interface ExchangeRatesDao {

    @Query("SELECT * FROM ExchangeRates")
    fun getConversionRates(): Single<ExchangeRatesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExchangeRates(exchangeRatesEntity: ExchangeRatesEntity): Single<Long>

    @Query("DELETE FROM ExchangeRates")
    fun deleteExchangeRates()

}