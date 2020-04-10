package tech.appclub.loanmanager.viewholders.loanlist

import androidx.recyclerview.widget.RecyclerView
import tech.appclub.loanmanager.data.CountryData
import tech.appclub.loanmanager.databinding.CurrencySpinnerItemViewBinding
import tech.appclub.loanmanager.view.CurrencySpinnerViewHolder

class CurrencySpinnerViewHolderImpl internal constructor(
    private val binding: CurrencySpinnerItemViewBinding
) : RecyclerView.ViewHolder(binding.root),
    CurrencySpinnerViewHolder {


    override fun bind(currencyData: CountryData) {
        binding.data = currencyData
        binding.executePendingBindings()
    }
}