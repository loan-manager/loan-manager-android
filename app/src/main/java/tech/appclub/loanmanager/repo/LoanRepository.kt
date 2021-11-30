package tech.appclub.loanmanager.repo

import androidx.lifecycle.LiveData
import tech.appclub.loanmanager.data.Loan
import tech.appclub.loanmanager.data.LoanDAO
import javax.inject.Inject

class LoanRepository @Inject constructor(private val loanDAO: LoanDAO) {

    val unpaidLoans: LiveData<List<Loan>> = loanDAO.getUnpaidLoans()
    val activeLoans: LiveData<List<Loan>> = loanDAO.getActiveLoans()
    val historyLoans: LiveData<List<Loan>> = loanDAO.getHistoryLoans()
    val paidLoans: LiveData<List<Loan>> = loanDAO.getPaidLoans()
    val unpaidLoanCount: LiveData<Int> = loanDAO.getUnpaidLoanCount()
    val unpaidLoanGrandTotal: LiveData<Double> = loanDAO.getUnpaidLoanGrandTotal()
    val paidLoanCount: LiveData<Int> = loanDAO.getPaidLoanCount()
    val paidLoanGrandTotal: LiveData<Double> = loanDAO.getPaidLoanGrandTotal()
    val allLoanCount: LiveData<Int> = loanDAO.getAllLoanCount()

    suspend fun insert(loan: Loan) {
        loanDAO.insert(loan)
    }

    suspend fun deleteAll() {
        loanDAO.deleteAll()
    }

//    suspend fun deleteLoan(loan: Loan) {
//        loanDAO.deleteLoan(loan)
//    }

    suspend fun updateLoan(loan: Loan) {
        loanDAO.updateLoan(loan)
    }

    suspend fun updateCurrency(code: String, currency: String, country: String, position: Int) {
        loanDAO.updateCurrency(code, currency, country, position)
    }

//    suspend fun currentLoan(loanId: Int): Loan {
//        return loanDAO.getLoan(loanId)
//    }

    suspend fun deleteAllPaidLoans() {
        return loanDAO.deleteAllPaidLoans()
    }

    suspend fun deleteHistory() {
        return loanDAO.deleteHistory()
    }

    suspend fun deleteUnPaidLoans() {
        return loanDAO.deleteAllUnPaidLoans()
    }


}