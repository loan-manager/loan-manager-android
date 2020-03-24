package tech.appclub.loanmanager.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.loan_item_view.view.*
import tech.appclub.loanmanager.view.LoanRowView
import java.math.BigDecimal
import java.text.DateFormat
import java.text.NumberFormat
import java.util.*

/*
* FILE: LoanViewHolder.kt
* DESC: LOAN RECYCLER ADAPTER VIEW HOLDER FUNCTIONS
* VERSION: v1.0
* PRODUCTION READY: FALSE
* AUTHOR: Arslan Mushtaq
* EMAIL: m.arslanmushtaqahmed@gmail.com
* */

class LoanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LoanRowView {

    override fun setId(id: String) {
        itemView.loanId.text = String.format(Locale.getDefault(), "ID: %s", id)
    }

    override fun setLoanHolder(name: String) {
        itemView.loanHolder.text = String.format(Locale.getDefault(), "Holder: %s", name)
    }

    override fun setLoanAmount(amount: BigDecimal) {
        itemView.loanAmount.text = NumberFormat.getCurrencyInstance().format(amount).toString()
    }

    override fun setReceiveDate(date: Date) {
        itemView.receivedDate.text = DateFormat.getDateInstance().format(date)
    }

    override fun setPaymentDate(date: Date) {
        itemView.paymentDate.text = DateFormat.getDateInstance().format(date)
    }

}