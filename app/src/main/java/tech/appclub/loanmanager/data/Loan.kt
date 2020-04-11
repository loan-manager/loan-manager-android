package tech.appclub.loanmanager.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.util.*

@Entity(tableName = "loan_table")
data class Loan(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @ColumnInfo(name = "loan_holder_name")
    var holder: String? = null,

    @ColumnInfo(name = "loan_amount")
    var amount: Double? = null,

    @ColumnInfo(name = "loan_received_date")
    var receivedOn: Date? = null,

    @ColumnInfo(name = "loan_payment_date")
    var paymentOn: Date? = null,

    @ColumnInfo(name = "loan_curreny_label")
    var currency: String? = null,

    @ColumnInfo(name = "loan_curreny_code")
    var code: String? = null,

    @ColumnInfo(name = "loan_curreny_country")
    var country: String? = null

)