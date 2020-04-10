package tech.appclub.loanmanager.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LoanDAO {

    @Query("SELECT * FROM loan_table ORDER BY loan_payment_date ASC")
    fun getAllLoans(): LiveData<List<Loan>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(loan: Loan)

    @Query("DELETE FROM loan_table")
    suspend fun deleteAll()

}