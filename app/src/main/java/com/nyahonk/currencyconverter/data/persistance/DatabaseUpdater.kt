package com.nyahonk.currencyconverter.data.persistance

import android.util.Log
import com.jakewharton.rxrelay2.BehaviorRelay
import com.nyahonk.currencyconverter.data.repository.ConversionRatesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlin.concurrent.thread

class DatabaseUpdater @Inject constructor(
    private val repository: ConversionRatesRepository
) {

    private val updateRelay =  BehaviorRelay.create<DBUpdateCode>()
    private lateinit var disposable: Disposable

    fun updateDatabase() {

        disposable = repository.updateConversionRatesData()
            .doOnSubscribe { updateRelay.accept(DBUpdateCode.UPDATE_STARTED) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate { disposable.dispose() }
            .subscribe(
                {
                    updateRelay.accept(DBUpdateCode.SUCCESS)
                    Log.d(javaClass.canonicalName, " database updated")
                },
                {
                    updateRelay.accept(DBUpdateCode.UPDATE_FAILED)
                    Log.d(javaClass.canonicalName, it.message ?: " error updating db" )
                }
            )
    }

    fun getUpdateObservable() = updateRelay
}