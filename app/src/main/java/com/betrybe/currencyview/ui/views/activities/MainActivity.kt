package com.betrybe.currencyview.ui.views.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.betrybe.currencyview.R
import com.betrybe.currencyview.common.ApiIdlingResource
import com.betrybe.currencyview.data.api.ApiService
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val textAutoComplete: MaterialAutoCompleteTextView by lazy {
        findViewById(R.id.currency_selection_input_layout)
    }

    private val textVisibility: TextView by lazy {
        findViewById(R.id.select_currency_state)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.apilayer.com/exchangerates_data/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)


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
}
