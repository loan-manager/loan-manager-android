package tech.appclub.loanmanager.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "loan_table")
class Loan {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @ColumnInfo(name = "loan_holder_name")
    var holder: String? = null

    @ColumnInfo(name = "loan_amount")
    var amount: Double? = null

    @ColumnInfo(name = "loan_received_date")
    var receivedOn: Date? = null

    @ColumnInfo(name = "loan_payment_date")
    var paymentOn: Date? = null

    @ColumnInfo(name = "loan_currency_label")
    var currency: String? = null

    @ColumnInfo(name = "loan_currency_code")
    var code: String? = null

    @ColumnInfo(name = "loan_currency_country")
    var country: String? = null

    @ColumnInfo(name = "loan_status")
    var status: Int? = 0

    @ColumnInfo(name = "country_position")
    var position: Int? = 0

}