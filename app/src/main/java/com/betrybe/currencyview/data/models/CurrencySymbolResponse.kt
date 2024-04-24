package com.betrybe.currencyview.data.models

data class CurrencySymbolResponse(
    val success: Boolean,
    val symbols: Map<String, String>
)
