package com.nyahonk.currencyconverter.data.persistance.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nyahonk.currencyconverter.CurrenciesMap
import kotlin.collections.HashMap

class ExchangeTypeConverter {
    private val hashMapTypeToken = object: TypeToken<LinkedHashMap<CurrenciesMap, Double>>() {}.type
    private val gson = Gson()

    @TypeConverter
    fun convertCurrencyMapToString(currenciesMap: CurrenciesMap) = currenciesMap.currency

    @TypeConverter
    fun convertStringToCurrencyMap(currency: String) = CurrenciesMap
        .values().find { it.currency == currency }

    @TypeConverter
    fun convertHashMapToString(hashMap: LinkedHashMap<CurrenciesMap, Double>) = gson.toJson(hashMap)

    @TypeConverter
    fun convertStringToHashMap(json: String) = gson.fromJson<LinkedHashMap<CurrenciesMap, Double>>(json, hashMapTypeToken)
}