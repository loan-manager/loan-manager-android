package tech.appclub.loanmanager.repo

import androidx.lifecycle.LiveData
import tech.appclub.loanmanager.data.Loan
import tech.appclub.loanmanager.data.LoanDAO


class LoanRepository(private val loanDAO: LoanDAO) {

    val allLoans: LiveData<List<Loan>> = loanDAO.getAllLoans()

    suspend fun insert(loan: Loan) {
        loanDAO.insert(loan)
    }

}