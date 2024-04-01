package com.betrybe.currencyview

import android.content.Context
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.betrybe.currencyview.common.ApiIdlingResource
import com.betrybe.currencyview.ui.views.activities.MainActivity
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

private fun getId(id: String): Int {
    val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
    val packageName: String = targetContext.packageName
    return targetContext.resources.getIdentifier(id, "id", packageName)
}

@RunWith(AndroidJUnit4::class)
class ApiRequestAndroidTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(ApiIdlingResource.getIdlingResource())
    }

    @After
    fun teardown() {
        IdlingRegistry.getInstance().unregister(ApiIdlingResource.getIdlingResource())
    }

    @Test
    fun test_req_8_realizar_requisicao_ao_endpoint_get_symbols_via_retrofit() {
        // Realiza uma operação de click no componente AutoCompleteTextView
        Espresso.onView(ViewMatchers.withId(getId("currency_selection_input_layout")))
            .perform(ViewActions.click())

        // O elemento MaterialTextView (select_currency_state) é exibido?
        Espresso.onView(ViewMatchers.withId(getId("select_currency_state")))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Seleciona a moeda BRL no AutoCompleteTextView
        Espresso.onData(Matchers.equalTo("BRL"))
            .inRoot(RootMatchers.isPlatformPopup())
            .perform(ViewActions.click())
    }

    @Test
    fun test_req_9_realizar_requisicao_ao_endpoint_get_latest_via_retrofit() {
        // Realiza uma operação de click no componente AutoCompleteTextView
        Espresso.onView(ViewMatchers.withId(getId("currency_selection_input_layout")))
            .perform(ViewActions.click())

        // O elemento MaterialTextView (select_currency_state) é exibido?
        Espresso.onView(ViewMatchers.withId(getId("select_currency_state")))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Seleciona a moeda BRL no AutoCompleteTextView
        Espresso.onData(Matchers.equalTo("BRL"))
            .inRoot(RootMatchers.isPlatformPopup())
            .perform(ViewActions.click())

        // O elemento RecyclerView é exibido?
        // O elemento RecyclerView possui, no mínimo, um elemento?
        Espresso.onView(ViewMatchers.withId(getId("currency_rates_state")))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .check(ViewAssertions.matches(ViewMatchers.hasMinimumChildCount(1)))
    }
}
