package tech.appclub.loanmanager.repo

import androidx.lifecycle.LiveData
import tech.appclub.loanmanager.data.Loan
import tech.appclub.loanmanager.data.LoanDAO

class LoanRepository(private val loanDAO: LoanDAO) {

    val unpaidLoans: LiveData<List<Loan>> = loanDAO.getUnpaidLoans()
    val paidLoans: LiveData<List<Loan>> = loanDAO.getPaidLoans()
    val totalLoans: LiveData<List<Loan>> = loanDAO.getAllLoans()
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

    suspend fun deleteLoan(loan: Loan) {
        loanDAO.deleteLoan(loan)
    }

    suspend fun updateLoan(loan: Loan) {
        loanDAO.updateLoan(loan)
    }

    suspend fun updateCurrency(code: String, currency: String, country: String) {
        loanDAO.updateCurrency(code, currency, country)
    }

    suspend fun currentLoan(loanId: Int) {
        loanDAO.getLoan(loanId)
    }


}