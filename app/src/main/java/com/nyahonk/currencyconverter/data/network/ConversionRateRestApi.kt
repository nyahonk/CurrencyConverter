package com.nyahonk.currencyconverter.data.network

import com.nyahonk.currencyconverter.CurrenciesMap
import com.nyahonk.currencyconverter.entity.ExchangeRatesEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ConversionRateRestApi {

    /**
     * @param base currency  abbreviature
     * @return exchange rates for specific base currency
     */
    @GET("latest")
    fun getConversionRates(
        @Query("base") base: CurrenciesMap
    ): Single<ExchangeRatesEntity>
}