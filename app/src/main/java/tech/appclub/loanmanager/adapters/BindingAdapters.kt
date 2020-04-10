package tech.appclub.loanmanager.adapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.util.*

@BindingAdapter("app:setAmount")
fun setAmount(view: TextView, amount: Double) {
    view.text = String.format(Locale.getDefault(), "Rs. %.0f", amount)
}