package com.nyahonk.currencyconverter.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.nyahonk.currencyconverter.App

fun Fragment.appComponent(): AppComponent {
    return (this.activity!!.application as App).appComponent
}

fun FragmentActivity.appComponent(): AppComponent {
    return (application as App).appComponent
}