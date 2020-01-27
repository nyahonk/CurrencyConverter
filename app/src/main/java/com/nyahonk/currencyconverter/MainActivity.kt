package com.nyahonk.currencyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : AppCompatActivity() {

    private val navigator by lazy {
        SupportAppNavigator(this, supportFragmentManager, R.id.container)
    }

    private val navigatorHolder: NavigatorHolder by lazy {
        (applicationContext as App).appComponent.navigatorHolder()
    }

    private val appLauncher: AppLauncher by lazy {

        (applicationContext as App).appComponent.appLauncher()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            appLauncher.coldStart()
        }
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}
