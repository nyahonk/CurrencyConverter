package com.nyahonk.currencyconverter.data.repository

import android.util.Log
import com.nyahonk.currencyconverter.CurrenciesMap
import com.nyahonk.currencyconverter.data.network.ConversionRateRestApi
import com.nyahonk.currencyconverter.data.persistance.db.dao.ConversionPairDao
import com.nyahonk.currencyconverter.data.persistance.db.dao.ExchangeRatesDao
import com.nyahonk.currencyconverter.entity.ExchangeRatesEntity
import io.reactivex.Single
import javax.inject.Inject

class ConversionRatesRepository @Inject constructor(
    private val api: ConversionRateRestApi,
    private val dataBase: ExchangeRatesDao
) {
    fun getConversionRates(): Single<ExchangeRatesEntity> =

        dataBase.getConversionRates()

    fun getConversionRatesOnly(): Single<LinkedHashMap<CurrenciesMap, Double>> =

        dataBase.getConversionRates().flatMap { Single.just(it.rates) }

    fun updateConversionRatesData(): Single<Long> =

        api.getConversionRates(CurrenciesMap.USD)
            .flatMap {
                Log.d(javaClass.canonicalName, " saving to database")
                dataBase.insertExchangeRates(it)
                    .onErrorReturnItem(-1L)
            }


}