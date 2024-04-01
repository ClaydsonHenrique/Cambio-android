package com.betrybe.currencyview

import org.junit.Test
import kotlin.reflect.full.createType
import kotlin.reflect.full.memberProperties
import kotlin.reflect.typeOf

class MainActivityTest {
    @Test
    fun test_req_7_criar_classes_que_representam_as_respostas_dos_endpoints_get_symbols_e_latest() {
        try {
            // Existe a data class CurrencySymbolResponse no pacote com.betrybe.currencyview.data.models?
            val currencySymbolResponseClassName =
                "com.betrybe.currencyview.data.models.CurrencySymbolResponse"

            val currencySymbolResponse = Class.forName(currencySymbolResponseClassName).kotlin
            // Existe a propriedade success do tipo Boolean na data class CurrencySymbolResponse?
            assert(
                currencySymbolResponse.memberProperties.any {
                    it.name == "success" && it.returnType == Boolean::class.createType()
                },
            )
            // Existe a propriedade symbols do tipo Map<String, CurrencyInfo> na data class CurrencySymbolResponse?
            assert(
                currencySymbolResponse.memberProperties.any {
                    it.name == "symbols" && it.returnType == typeOf<Map<String, String>>()
                },
            )
        } catch (e: ClassNotFoundException) {
            throw AssertionError("A classe Collaborator não existe", e)
        }

        try {
            // Existe a data class CurrencyRateResponse no pacote com.betrybe.currencyview.data.models?
            val currencyRateResponseClassName =
                "com.betrybe.currencyview.data.models.CurrencyRateResponse"
            val currencyRateResponse = Class.forName(currencyRateResponseClassName).kotlin
            // Existe a propriedade success do tipo Boolean na data class CurrencyRateResponse?
            assert(
                currencyRateResponse.memberProperties.any {
                    it.name == "success" && it.returnType == Boolean::class.createType()
                },
            )
            // Existe a propriedade base do tipo String na data class CurrencyRateResponse?
            assert(
                currencyRateResponse.memberProperties.any {
                    it.name == "base" && it.returnType == String::class.createType()
                },
            )
            // Existe a propriedade date do tipo String na data class CurrencyRateResponse?
            assert(
                currencyRateResponse.memberProperties.any {
                    it.name == "date" && it.returnType == String::class.createType()
                },
            )
            // Existe a propriedade rates do tipo Map<Stirng, Double> na data class CurrencyRateResponse?
            assert(
                currencyRateResponse.memberProperties.any {
                    it.name == "rates" && it.returnType == typeOf<Map<String, Double>>()
                },
            )
        } catch (e: ClassNotFoundException) {
            throw AssertionError("A classe Collaborator não existe", e)
        }
    }
}
