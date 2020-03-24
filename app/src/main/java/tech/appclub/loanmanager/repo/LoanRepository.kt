package tech.appclub.loanmanager.repo

import tech.appclub.loanmanager.data.Loan

/*
* FILE: LoanRepository.kt (INTERFACE)
* DESC: REPOSITORY INTERFACE FOR LOADING LOAN DATA MODEL
* VERSION: v1.1
* PRODUCTION READY: FALSE
* AUTHOR: Arslan Mushtaq
* EMAIL: m.arslanmushtaqahmed@gmail.com
* */

interface LoanRepository {

    fun loadLoans(): List<Loan>

}