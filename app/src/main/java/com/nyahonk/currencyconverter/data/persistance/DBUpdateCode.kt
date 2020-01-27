package com.nyahonk.currencyconverter.data.persistance

enum class DBUpdateCode(val code: Int) {
    UPDATE_STARTED(0),
    NO_INTERNET(1),
    SUCCESS(2),
    UPDATE_FAILED (3),
    DATABASE_EMPTY(4)
}