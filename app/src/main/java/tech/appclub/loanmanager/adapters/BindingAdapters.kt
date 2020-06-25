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
import tech.appclub.loanmanager.utils.NumbersToWords
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
    when {
        LocalDate(loan.paymentOn!!) == LocalDate.now() -> {
            view.text = view.context.getString(R.string.today)
        }
        else -> {
            view.text = formatDate(loan.paymentOn!!)
        }
    }
}

@BindingAdapter("app:setPaymentLabel")
fun setPaymentLabel(view: TextView, status: Int) {
    when (status) {
        0 -> view.text = view.context.resources.getString(R.string.cancelled_on)
        1 -> view.text = view.context.resources.getString(R.string.paid_on)
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
            view.setTextColor(ContextCompat.getColor(view.context, android.R.color.holo_red_dark))
        }
        daysCount < -1 -> {
            view.text = String.format("%d days up", abs(daysCount))
            view.setTextColor(ContextCompat.getColor(view.context, android.R.color.holo_red_dark))
        }
        daysCount == 0 -> {
            view.text = String.format("Today")
            view.setTextColor(ContextCompat.getColor(view.context, android.R.color.holo_green_dark))
        }
        daysCount == 1 -> {
            view.text = String.format("Tomorrow")
            view.setTextColor(ContextCompat.getColor(view.context, android.R.color.holo_green_dark))
        }
        daysCount > 1 -> {
            view.text = String.format("%d days left", daysCount)
        }
    }
}

@BindingAdapter("app:setReceivingTitle")
fun setReceivingTitle(view: TextView, situation: Int) {
    when (situation) {
        0 -> view.text = view.context.getString(R.string.lent_on)
        1 -> view.text = view.context.getString(R.string.borrowed_on)
        else -> view.text = view.context.getString(R.string.unknown)
    }
}

@BindingAdapter("app:setPaymentTitle")
fun setPaymentTitle(view: TextView, situation: Int) {
    when (situation) {
        0 -> view.text = view.context.getString(R.string.receiving_on)
        1 -> view.text = view.context.getString(R.string.payment_due_till)
        else -> view.text = view.context.getString(R.string.unknown)
    }
}

@BindingAdapter("app:setPaidTitle")
fun setPaidTitle(view: TextView, situation: Int) {
    when (situation) {
        0 -> view.text = view.context.getString(R.string.received_on)
        1 -> view.text = view.context.getString(R.string.paid_on)
    }
}

@BindingAdapter("app:setStatusTitle")
fun setStatusTitle(view: TextView, status: Int) {
    if (status == 0) {
        view.background = view.context.getDrawable(R.drawable.unpaid_bg)
    } else {
        view.background = view.context.getDrawable(R.drawable.paid_bg)
    }
    view.text = getPaymentStatus(status)
}

@BindingAdapter("app:setAmountInWords")
fun setAmountInWords(view: TextView, amount: Double) {
    view.text = NumbersToWords.convert(amount.toLong()).toUpperCase(Locale.getDefault())
}

@BindingAdapter("app:setSituation")
fun setSituation(view: TextView, situation: Int) {
    if (situation == 0) {
        view.background = view.context.getDrawable(R.drawable.given_bg)
    } else {
        view.background = view.context.getDrawable(R.drawable.borrowed_bg)
    }
    view.text = getSituation(situation)
}

private fun getSituation(situation: Int): String {
    return when (situation) {
        0 -> "Given"
        1 -> "Borrowed"
        else -> "Unknown"
    }
}

private fun getPaymentStatus(status: Int): String {
    return when (status) {
        0 -> "Unpaid"
        1 -> "Paid"
        else -> "Unknown"
    }
}

private fun daysCheck(startDate: Date, finishDate: Date): Int {
    if (startDate.time > Date().time) {
        return Days.daysBetween(LocalDate(finishDate.time), LocalDate(startDate.time))
            .negated().days
    }
    return Days.daysBetween(LocalDate(finishDate.time), LocalDate()).negated().days
}