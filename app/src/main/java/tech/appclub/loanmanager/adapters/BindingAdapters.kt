package tech.appclub.loanmanager.adapters

import android.content.Context
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputEditText
import tech.appclub.loanmanager.R
import tech.appclub.loanmanager.data.Loan
import tech.appclub.loanmanager.utils.DateTimeUtils.Companion.formatDate
import java.text.DecimalFormat
import java.util.*
import kotlin.math.abs

@BindingAdapter("app:setAmount")
fun setAmount(view: TextView, data: Loan) {
    val decimalFormat = DecimalFormat()
    view.text =
        String.format(Locale.getDefault(), "%s %s", data.code, decimalFormat.format(data.amount))
}

@BindingAdapter("app:setEditAmount")
fun setEditAmount(view: TextInputEditText, data: Loan) {
    val decimalFormat = DecimalFormat()
    view.setText(decimalFormat.format(data.amount), TextView.BufferType.EDITABLE)
}

@BindingAdapter("app:setCountry")
fun setCountry(view: Spinner, data: Loan) {
    view.setSelection(data.position!!)
}

@BindingAdapter("app:setReceiveDate")
fun setReceiveDate(view: TextView, date: Date) {
    view.text = formatDate(date)
}

@BindingAdapter("app:setPaymentDate")
fun setPaymentDate(view: TextView, loan: Loan) {
    if (loan.status == 2) {
        view.text = String.format("Cancelled on %s", formatDate(loan.paymentOn!!))
        view.setTextColor(ContextCompat.getColor(view.context, android.R.color.holo_red_dark))
    } else {
        view.text = formatDate(loan.paymentOn!!)
    }
}

@BindingAdapter("app:setDaysLeft")
fun setDaysLeft(view: TextView, loan: Loan) {
    if (daysLeft(loan.receivedOn!!, loan.paymentOn!!) < 0) {
        view.text = String.format(
            "%s days up",
            abs(daysLeft(loan.receivedOn!!, loan.paymentOn!!)).toString()
        )
        view.background = ContextCompat.getDrawable(view.context, R.drawable.error_textview_bg)
    } else if (daysLeft(loan.receivedOn!!, loan.paymentOn!!) > -1
        && daysLeft(loan.receivedOn!!, loan.paymentOn!!) < 1
    ) {
        view.text = String.format("Today")
    } else {
        view.text =
            String.format("%s days left", daysLeft(loan.receivedOn!!, loan.paymentOn!!).toString())
    }
}

@BindingAdapter("app:setBackground")
fun setBackground(view: MaterialCardView, loan: Loan) {
    when (loan.status) {
        0 -> view.strokeColor = updateStrokeColor(view.context, android.R.color.holo_purple)
        1 -> view.strokeColor = updateStrokeColor(view.context, android.R.color.holo_green_dark)
        2 -> view.strokeColor = updateStrokeColor(view.context, android.R.color.holo_purple)
        else -> view.strokeColor = updateStrokeColor(view.context, android.R.color.holo_purple)
    }
}

private fun updateStrokeColor(context: Context, color: Int): Int {
    return ContextCompat.getColor(context, color)
}

private fun daysLeft(startDate: Date, finishDate: Date): Long {
    val difference: Long
    if (startDate.time > Date().time) {
        difference = finishDate.time - startDate.time
    } else {
        difference = finishDate.time - Date().time
    }
    val seconds = difference / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    return hours / 24
}