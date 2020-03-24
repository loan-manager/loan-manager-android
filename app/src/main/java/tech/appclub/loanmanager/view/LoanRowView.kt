package tech.appclub.loanmanager.view

import java.math.BigDecimal
import java.util.*

/*
* FILE: LoanRowView.kt (INTERFACE)
* DESC: LOAN RECYCLER ADAPTER ROW VIEW HOLDER FUNCTIONS
* VERSION: v1.0
* PRODUCTION READY: FALSE
* AUTHOR: Arslan Mushtaq
* EMAIL: m.arslanmushtaqahmed@gmail.com
* */

interface LoanRowView {

    fun setId(id: String)
    fun setLoanHolder(name: String)
    fun setLoanAmount(amount: BigDecimal)
    fun setReceiveDate(date: Date)
    fun setPaymentDate(date: Date)

}