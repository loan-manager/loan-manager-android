package tech.appclub.loanmanager

import tech.appclub.loanmanager.repo.LoanRepository
import tech.appclub.loanmanager.repo.LoanRepositoryImpl

/*
* FILE: DependencyInjectorImpl.kt
* DESC: DEPENDENCY INJECTOR IMPLEMENTATION
* VERSION: v1.0
* PRODUCTION READY: FALSE
* AUTHOR: Arslan Mushtaq
* EMAIL: m.arslanmushtaqahmed@gmail.com
* */

class DependencyInjectorImpl : DependencyInjector {

    override fun loanRepository(): LoanRepository {
        return LoanRepositoryImpl()
    }

}