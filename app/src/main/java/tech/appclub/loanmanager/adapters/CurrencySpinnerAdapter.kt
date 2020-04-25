package tech.appclub.loanmanager.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import tech.appclub.loanmanager.data.Country
import tech.appclub.loanmanager.databinding.CurrencySpinnerItemViewBinding

class CurrencySpinnerAdapter(
    context: Context,
    private val data: List<Country>
) : BaseAdapter() {

    private val inflater = LayoutInflater.from(context)

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = CurrencySpinnerItemViewBinding.inflate(inflater, parent, false)
        val viewHolder = CurrencySpinnerViewHolder(view)
        viewHolder.bind(data[position])
        return view.root
    }

    override fun getItem(position: Int): Country {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount() = data.size

    inner class CurrencySpinnerViewHolder(private val binding: CurrencySpinnerItemViewBinding) {

        fun bind(country: Country) {
            binding.data = country
            binding.executePendingBindings()
        }

    }
}