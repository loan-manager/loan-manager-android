package tech.appclub.loanmanager.adapters

import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import org.joda.time.Days
import org.joda.time.LocalDate
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
    view.text = formatDate(loan.paymentOn!!)
}

@BindingAdapter("app:setPaymentLabel")
fun setPaymentLabel(view: TextView, loan: Loan) {
    view.setTextColor(ContextCompat.getColor(view.context, android.R.color.white))
    when (loan.status) {
        0 -> view.text = view.context.resources.getString(R.string.payment_on)
        1 -> view.text = view.context.resources.getString(R.string.paid_on)
        2 -> view.text = view.context.resources.getString(R.string.cancelled_on)
        else -> view.text = view.context.resources.getString(R.string.unknown_status)
    }
}

@BindingAdapter("app:setLayoutBackground")
fun setLayoutBackground(view: LinearLayout, loan: Loan) {
    when (loan.status) {
        1 -> view.setBackgroundResource(R.drawable.green_textview_bg)
        2 -> view.setBackgroundResource(R.drawable.error_textview_bg)
        else -> view.setBackgroundResource(R.drawable.textview_bg)
    }
}

@BindingAdapter("app:setDaysLeft")
fun setDaysLeft(view: TextView, loan: Loan) {
    val daysCount = daysCheck(loan.receivedOn!!, loan.paymentOn!!)
    when {
        daysCount == -1 -> {
            view.text = String.format("Yesterday")
            view.background = ContextCompat.getDrawable(view.context, R.drawable.error_textview_bg)
        }
        daysCount < -1 -> {
            view.text = String.format("%d days up", abs(daysCount))
            view.background = ContextCompat.getDrawable(view.context, R.drawable.error_textview_bg)
        }
        daysCount == 0 -> {
            view.text = String.format("Today")
        }
        daysCount == 1 -> {
            view.text = String.format("Tomorrow")
        }
        daysCount > 1 -> {
            view.text = String.format("%d days left", daysCount)
        }
    }
}

//private fun daysCount(startDate: Date, finishDate: Date): Long {
//    val difference: Long = if (startDate.time > Date().time) {
//        finishDate.time - startDate.time
//    } else {
//        finishDate.time - Date().time
//    }
//    val seconds = difference / 1000
//    val minutes = seconds / 60
//    val hours = minutes / 60
//    return hours / 24
//}

private fun daysCheck(startDate: Date, finishDate: Date): Int {
    if (startDate.time > Date().time) {
        return Days.daysBetween(LocalDate(finishDate.time), LocalDate(startDate.time)).negated().days
    }
    return Days.daysBetween(LocalDate(finishDate.time), LocalDate()).negated().days
}