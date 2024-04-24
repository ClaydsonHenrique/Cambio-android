package com.betrybe.currencyview.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.betrybe.currencyview.R
import com.google.android.material.textview.MaterialTextView

class AdapterCurrencyRater(private val rates:Map<String, Double>)
    : Adapter<AdapterCurrencyRater.CurrencyRatesViewHolder>() {

        private val rateLists: List<String> = rates.keys.toList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CurrencyRatesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.currency_rates_layout, parent, false)
        return  CurrencyRatesViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CurrencyRatesViewHolder,
        position: Int
    ) {
        val date = rateLists[position]
        holder.textCurrencyName.text = date
        holder.textCurrencyRate.text = rates[date].toString()
    }

    override fun getItemCount(): Int {
        return rates.size
    }


    class CurrencyRatesViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val textCurrencyName : MaterialTextView = view.findViewById(R.id.currency_name)
        val textCurrencyRate : MaterialTextView = view.findViewById(R.id.currency_rate)
    }

}