package tech.appclub.loanmanager.adapters

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
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

@BindingAdapter("app:setReceiveDate")
fun setReceiveDate(view: TextView, date: Date) {
    view.text = String.format("Received on %s", formatDate(date))
}

@BindingAdapter("app:setPaymentDate")
fun setPaymentDate(view: TextView, loan: Loan) {
    if (loan.status == 2) {
        view.text = String.format("Cancelled on %s", formatDate(loan.paymentOn!!))
        view.setTextColor(ContextCompat.getColor(view.context, android.R.color.holo_red_dark))
    } else {
        view.text = String.format("Return on %s", formatDate(loan.paymentOn!!))
    }
}

@BindingAdapter("app:setDaysLeft")
fun setDaysLeft(view: TextView, loan: Loan) {
    if (daysLeft(loan.receivedOn!!, loan.paymentOn!!) < 0) {
        view.text = String.format(
            "%s days up",
            abs(daysLeft(loan.receivedOn!!, loan.paymentOn!!)).toString()
        )
        view.setTextColor(ContextCompat.getColor(view.context, android.R.color.holo_red_dark))
    } else if (daysLeft(loan.receivedOn!!, loan.paymentOn!!) > -1
        && daysLeft(loan.receivedOn!!, loan.paymentOn!!) < 1
    ) {
        view.text = String.format("Today")
        view.setTextColor(ContextCompat.getColor(view.context, android.R.color.holo_green_dark))
    } else {
        view.text =
            String.format("%s days left", daysLeft(loan.receivedOn!!, loan.paymentOn!!).toString())
    }
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
    val days = hours / 24
    return days
}