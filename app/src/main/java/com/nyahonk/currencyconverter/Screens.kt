package com.nyahonk.currencyconverter


import com.nyahonk.currencyconverter.ui.currencyConversionFragment.CurrencyConverterFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    object CurrencyConverterMainScreen : SupportAppScreen() {
        override fun getFragment() =
            CurrencyConverterFragment()
    }
}