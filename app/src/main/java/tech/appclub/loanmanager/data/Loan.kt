package tech.appclub.loanmanager.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


/*
*
* LOAN STATUS INTEGERS:
* 0 - Unpaid loans
* 1 - paid loans
* 2 - Active loans
*
* */

/*
*
* LOAN SITUATION INTEGERS:
* 0 - Given
* 1 - Borrowed
*
* */

@Entity(tableName = "loan_table")
class Loan() : Parcelable {

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

    @ColumnInfo(name = "loan_situation")
    var situation: Int? = 0

    @ColumnInfo(name = "country_position")
    var position: Int? = 0

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        holder = parcel.readString()
        amount = parcel.readValue(Double::class.java.classLoader) as? Double
        currency = parcel.readString()
        code = parcel.readString()
        country = parcel.readString()
        status = parcel.readValue(Int::class.java.classLoader) as? Int
        situation = parcel.readValue(Int::class.java.classLoader) as? Int
        position = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(holder)
        parcel.writeValue(amount)
        parcel.writeString(currency)
        parcel.writeString(code)
        parcel.writeString(country)
        parcel.writeValue(status)
        parcel.writeValue(situation)
        parcel.writeValue(position)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Loan> {
        override fun createFromParcel(parcel: Parcel): Loan {
            return Loan(parcel)
        }

        override fun newArray(size: Int): Array<Loan?> {
            return arrayOfNulls(size)
        }
    }

}