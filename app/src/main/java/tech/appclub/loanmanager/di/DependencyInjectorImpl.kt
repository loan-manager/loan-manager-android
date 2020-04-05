package tech.appclub.loanmanager.di

import tech.appclub.loanmanager.repo.LoanRepository
import tech.appclub.loanmanager.repo.LoanRepositoryImpl


class DependencyInjectorImpl : DependencyInjector {

    override fun loanRepository(): LoanRepository {
        return LoanRepositoryImpl()
    }

}