package tech.appclub.loanmanager.adapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import org.w3c.dom.Text
import tech.appclub.loanmanager.data.Loan
import tech.appclub.loanmanager.utils.DateTimeUtils.Companion.formatDate
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

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
fun setPaymentDate(view: TextView, date: Date) {
    view.text = String.format("Return on %s", formatDate(date))
}

@BindingAdapter("app:setDaysLeft")
fun setDaysLeft(view: TextView, loan: Loan) {
    view.text = String.format("%s days left", daysLeft(loan.receivedOn!!, loan.paymentOn!!))
}

private fun daysLeft(startDate: Date, finishDate: Date): String {
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
    return days.toString()
}