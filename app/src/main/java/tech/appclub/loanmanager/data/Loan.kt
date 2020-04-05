package tech.appclub.loanmanager.data

import java.math.BigDecimal
import java.util.*

data class Loan(
    val id: String, val holder: String?, val amount: BigDecimal?,
    val receivedOn: Date?, val paymentOn: Date?
)