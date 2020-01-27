package com.nyahonk.currencyconverter

import com.nyahonk.currencyconverter.data.persistance.DatabaseUpdater
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class AppLauncher @Inject constructor(
    private val dbUpdater: DatabaseUpdater,
    private val router: Router
) {
    fun coldStart() {
        dbUpdater.updateDatabase()
        router.newRootScreen(Screens.CurrencyConverterMainScreen)
    }
}