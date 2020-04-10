package tech.appclub.loanmanager.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.util.*

@Entity(tableName = "loan_table")
data class Loan(
    @PrimaryKey
    var id: Int = 0,

    @ColumnInfo(name = "loan_holder_name")
    var holder: String = "",

    @ColumnInfo(name = "loan_amount")
    var amount: Double = 0.0,

    @ColumnInfo(name = "loan_received_date")
    var receivedOn: Date = Date(),

    @ColumnInfo(name = "loan_payment_date")
    var paymentOn: Date = Date(),

    @ColumnInfo(name = "loan_curreny_label")
    var currency: String = "",

    @ColumnInfo(name = "loan_curreny_code")
    var code: String = "",

    @ColumnInfo(name = "loan_curreny_country")
    var country: String = ""

)