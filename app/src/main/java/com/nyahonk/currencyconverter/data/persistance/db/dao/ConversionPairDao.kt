package com.nyahonk.currencyconverter.data.persistance.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nyahonk.currencyconverter.entity.CurrencyValueEntity
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface ConversionPairDao {

    @Query("SELECT * FROM CurrencyValueEntities WHERE currencyname = :name")
    fun getConversionPairByName(name: String): Flowable<CurrencyValueEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrencyValueEntity(currencyValueEntity: CurrencyValueEntity): Single<Long>

    @Query("DELETE FROM CurrencyValueEntities")
    fun deleteAllPairs()
}