package com.betrybe.currencyview.data.api

import com.betrybe.currencyview.data.models.CurrencySymbolResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {
    @Headers("apiKey: 20EyGYInJopKHhX7HzFTsiWv41CbMtdF")
    @GET("symbols")
    suspend fun getSymbols(): Response<CurrencySymbolResponse>
}