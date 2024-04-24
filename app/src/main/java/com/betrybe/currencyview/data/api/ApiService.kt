package com.betrybe.currencyview.data.api

import com.betrybe.currencyview.data.models.CurrencyRateResponse
import com.betrybe.currencyview.data.models.CurrencySymbolResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers("apiKey: 20EyGYInJopKHhX7HzFTsiWv41CbMtdF")
    @GET("symbols")
    suspend fun getSymbols(): Response<CurrencySymbolResponse>

    @Headers("apiKey: 20EyGYInJopKHhX7HzFTsiWv41CbMtdF")
    @GET("latest")
    suspend fun getLatest(@Query("base") base:String) : Response<CurrencyRateResponse>
}