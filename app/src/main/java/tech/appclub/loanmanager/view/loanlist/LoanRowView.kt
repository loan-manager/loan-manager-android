package tech.appclub.loanmanager.view.loanlist

import tech.appclub.loanmanager.data.Loan
import java.math.BigDecimal
import java.util.*

interface LoanRowView {

    fun bind(loan: Loan)

}