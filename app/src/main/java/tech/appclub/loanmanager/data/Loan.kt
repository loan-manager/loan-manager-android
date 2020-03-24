package tech.appclub.loanmanager.data

import java.math.BigDecimal
import java.util.*

/*
* FILE: Loan.kt
* DESC: LOAN DATA MODEL CLASS
* VERSION: v1.0
* PRODUCTION READY: FALSE
* AUTHOR: Arslan Mushtaq
* EMAIL: m.arslanmushtaqahmed@gmail.com
* */

data class Loan(
    val id: String, val holder: String?, val amount: BigDecimal?,
    val receivedOn: Date?, val paymentOn: Date?
)