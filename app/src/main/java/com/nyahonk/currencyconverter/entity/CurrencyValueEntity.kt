package com.nyahonk.currencyconverter.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CurrencyValueEntities")
data class CurrencyValueEntity(
    @PrimaryKey
    @ColumnInfo(name = "currencyname")
    val currencyName: String,
    @ColumnInfo(name = "currencyrate")
    val currencyValue: Double
    )