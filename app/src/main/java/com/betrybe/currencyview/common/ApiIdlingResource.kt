package com.betrybe.currencyview.common

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

object ApiIdlingResource {
    private const val RESOURCE = "API_CALLS"
    private val countingIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        countingIdlingResource.decrement()
    }

    fun getIdlingResource(): IdlingResource {
        return countingIdlingResource
    }
}
