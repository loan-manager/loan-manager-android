package tech.appclub.loanmanager

import tech.appclub.loanmanager.repo.LoanRepository

/*
* FILE: DependencyInjector.kt
* DESC: TEST BASED DEPENDENCY INJECTOR
* VERSION: v1.0
* PRODUCTION READY: FALSE
* AUTHOR: Arslan Mushtaq
* EMAIL: m.arslanmushtaqahmed@gmail.com
* */

interface DependencyInjector {

    fun loanRepository(): LoanRepository

}