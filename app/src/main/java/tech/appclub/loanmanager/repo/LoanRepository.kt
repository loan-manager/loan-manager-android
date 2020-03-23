package tech.appclub.loanmanager.repo

import tech.appclub.loanmanager.data.Loan

/*
* FILE: LoanRepository.kt
* DESC: REPOSITORY INTERFACE FOR LOADING LOAN DATA MODEL
* VERSION: v1.0
* PRODUCTION READY: FALSE
* AUTHOR: Arslan Mushtaq
* EMAIL: m.arslanmushtaqahmed@gmail.com
* */

interface LoanRepository {

    fun loadLoans(): Loan

}