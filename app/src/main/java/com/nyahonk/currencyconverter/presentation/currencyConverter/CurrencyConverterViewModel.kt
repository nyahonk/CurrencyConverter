package com.nyahonk.currencyconverter.presentation.currencyConverter

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.nyahonk.currencyconverter.CurrenciesMap
import com.nyahonk.currencyconverter.data.persistance.DBUpdateCode
import com.nyahonk.currencyconverter.data.persistance.DatabaseUpdater
import com.nyahonk.currencyconverter.interactor.currencyConverter.CurrencyConverterInteractor
import com.nyahonk.currencyconverter.presentation._base.BaseViewModel
import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject

class CurrencyConverterViewModel @Inject constructor(
    private val interactor: CurrencyConverterInteractor,
    private val updater: DatabaseUpdater
) : BaseViewModel() {

    //output
    val targetValue = BehaviorRelay.create<String>()
    val error = PublishRelay.create<DBUpdateCode>()

    //input
    fun onCalculateButtonClick(source:CurrenciesMap, target: CurrenciesMap, amount: String) {
        updater.getUpdateObservable().subscribe {
            if(it == DBUpdateCode.UPDATE_STARTED) handleError(it)
            else interactor.getConversionRatesOnly()
                .subscribe(
                    { currencies ->
                        if (source == CurrenciesMap.USD) {
                            (amount.toDouble() * currencies.getValue(target))
                        } else {
                            (amount.toDouble() / currencies.getValue(source) * currencies.getValue(target))
                        }.let {
                            val df = DecimalFormat("#.##")
                            df.roundingMode = RoundingMode.CEILING

                            targetValue.accept(df.format(it))
                        }
                    },
                    {
                        handleError(DBUpdateCode.DATABASE_EMPTY)
                    }
                ).connect()
        }.connect()

    }

    //internal
    init {
        updater.getUpdateObservable().subscribe {
            if (it != DBUpdateCode.UPDATE_STARTED) handleError(it)
        }.connect()

    }

    private fun handleError(errorCode: DBUpdateCode) {
        error.accept(errorCode)
    }
}