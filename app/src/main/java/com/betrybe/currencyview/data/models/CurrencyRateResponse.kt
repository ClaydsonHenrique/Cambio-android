package com.betrybe.currencyview.data.models

data class CurrencyRateResponse(
    val success: Boolean,
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)
