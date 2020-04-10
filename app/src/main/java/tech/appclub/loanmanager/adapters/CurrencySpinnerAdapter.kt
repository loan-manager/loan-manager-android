package tech.appclub.loanmanager.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import tech.appclub.loanmanager.data.CountryData
import tech.appclub.loanmanager.databinding.CurrencySpinnerItemViewBinding
import tech.appclub.loanmanager.viewholders.loanlist.CurrencySpinnerViewHolderImpl

class CurrencySpinnerAdapter(
    private val context: Context,
    private val data: List<CountryData>
) : BaseAdapter() {

    private val inflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = CurrencySpinnerItemViewBinding.inflate(inflater, parent, false)
        val viewHolder = CurrencySpinnerViewHolderImpl(view!!)
        viewHolder.bind(data[position])
        return view.root
    }


    override fun getItem(position: Int): CountryData {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }
}