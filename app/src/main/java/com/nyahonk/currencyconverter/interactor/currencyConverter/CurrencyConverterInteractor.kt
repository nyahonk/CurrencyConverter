package com.nyahonk.currencyconverter.interactor.currencyConverter

import com.nyahonk.currencyconverter.CurrenciesMap
import com.nyahonk.currencyconverter.data.repository.ConversionRatesRepository
import com.nyahonk.currencyconverter.entity.ExchangeRatesEntity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CurrencyConverterInteractor @Inject constructor(
    private val repository: ConversionRatesRepository
) {

    fun getConversionRates(): Single<ExchangeRatesEntity> =
        repository.getConversionRates()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getConversionRatesOnly(source:CurrenciesMap, target: CurrenciesMap, amount: String): Single<Double> =
        repository.getConversionRatesOnly()
            .map { currencies ->
                if (source == CurrenciesMap.USD) {
                    (amount.toDouble() * currencies.getValue(target))
                } else {
                    (amount.toDouble() / currencies.getValue(source) * currencies.getValue(target))
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}