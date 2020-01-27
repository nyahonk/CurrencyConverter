package com.nyahonk.currencyconverter.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.google.gson.annotations.SerializedName
import com.nyahonk.currencyconverter.CurrenciesMap

@Entity(tableName = "ExchangeRates")
data class ExchangeRatesEntity(
    @SerializedName("base")
    @PrimaryKey
    val base: CurrenciesMap,
    @SerializedName("date")
    val date: String?,
    @SerializedName("rates")
    val rates: LinkedHashMap<CurrenciesMap, Double>?
)