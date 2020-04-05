package tech.appclub.loanmanager.di

import tech.appclub.loanmanager.repo.LoanRepository

interface DependencyInjector {

    fun loanRepository(): LoanRepository

}