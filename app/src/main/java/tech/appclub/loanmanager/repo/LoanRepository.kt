package tech.appclub.loanmanager.repo

import androidx.lifecycle.LiveData
import tech.appclub.loanmanager.data.Loan
import tech.appclub.loanmanager.data.LoanDAO


class LoanRepository(private val loanDAO: LoanDAO) {

    val unpaidLoans: LiveData<List<Loan>> = loanDAO.getUnpaidLoans()
    val paidLoans: LiveData<List<Loan>> = loanDAO.getPaidLoans()
    val totalLoans: LiveData<List<Loan>> = loanDAO.getAllLoans()
    val totalLoanCount: LiveData<Int> = loanDAO.totalLoanCount()
    val grandLoanAmount: LiveData<Double> = loanDAO.grandLoanAmount()

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

}