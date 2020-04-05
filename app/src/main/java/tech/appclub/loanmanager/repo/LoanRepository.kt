package tech.appclub.loanmanager.repo

import tech.appclub.loanmanager.data.Loan

interface LoanRepository {

    fun loadLoans(): List<Loan>

}