package tech.appclub.loanmanager.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface LoanDAO {

    @Query("SELECT * FROM loan_table ORDER BY loan_payment_date ASC")
    fun getAllLoans(): LiveData<List<Loan>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(loan: Loan)

    @Query("DELETE FROM loan_table")
    fun deleteAll()

    @Delete
    fun deleteLoan(loan: Loan)

    @Update
    fun updateLoan(loan: Loan)

    @Query("SELECT COUNT(*) FROM loan_table")
    fun totalLoanCount(): Int

    @Query("SELECT SUM(loan_amount) FROM loan_table")
    fun totalLoanAmount(): Double

}