package com.betrybe.currencyview.ui.views.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.betrybe.currencyview.R
import com.betrybe.currencyview.common.ApiIdlingResource
import com.betrybe.currencyview.data.api.ApiService
import com.betrybe.currencyview.ui.adapters.AdapterCurrencyRater
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textview.MaterialTextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val textAutoComplete: MaterialAutoCompleteTextView by lazy {
        findViewById(R.id.currency_selection_input_layout)
    }

    private val textVisibility: MaterialTextView by lazy {
        findViewById(R.id.select_currency_state)
    }

    private val circleTime: FrameLayout by lazy {
        findViewById(R.id.waiting_response_state)
    }

    private val recyclerview : RecyclerView by lazy {
        findViewById(R.id.currency_rates_state)
    }

   private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.apilayer.com/exchangerates_data/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        CoroutineScope(Main).launch {
            try {

                ApiIdlingResource.increment()
                val symbolsResponse = apiService.getSymbols()
                val symbolsBody = symbolsResponse.body()

                val symbolsList = symbolsBody!!.symbols.keys.toList()
                val adapter = ArrayAdapter(
                    this@MainActivity,
                    android.R.layout.simple_dropdown_item_1line,
                    symbolsList
                )

                textAutoComplete.setAdapter(adapter)
                textVisibility.visibility = View.VISIBLE

                textAutoComplete.setOnItemClickListener { _, _, position, _ ->
                    val selectedCurrency = symbolsList[position]

                    getCurrencyRates(selectedCurrency)
                }


                ApiIdlingResource.decrement()
            } catch (e: HttpException) {

                ApiIdlingResource.decrement()
                Log.i("Response Api", "Error HttpException: ${e.message()}")

            } catch (e: IOException) {

                ApiIdlingResource.decrement()
                Log.i("Response Api", "Error IOException ${e.message}")

            }
        }

    }
    private fun getCurrencyRates (selected: String) {
        CoroutineScope(Main).launch {
            try {

                // ADICIONAR ESSA LINHA
                ApiIdlingResource.increment()

                val rateResponse = apiService.getLatest(selected)
                val ratesBody = rateResponse.body()?.rates

                if(ratesBody != null){
                    withContext(Main){
                        val adapter = AdapterCurrencyRater(ratesBody)
                        recyclerview.adapter = adapter
                        textVisibility.visibility = View.GONE
                        recyclerview.visibility = View.VISIBLE
                        recyclerview.layoutManager= GridLayoutManager(this@MainActivity, 2)
                    }


                }

                ApiIdlingResource.decrement()
            } catch (e: HttpException) {
                // ADICIONAR ESSA LINHA
                ApiIdlingResource.decrement()
                Log.i("Response Api", "Error HttpException: ${e.message()}")
                //...
                // Seu Codigo de erro de HttpException
                // ...
            } catch (e: IOException) {
                // ADICIONAR ESSA LINHA
                ApiIdlingResource.decrement()
                Log.i("Response Api", "Error IOException ${e.message}")
                //...
                // Seu Codigo de erro de IOException
                // ...
            }
        }
    }
}
