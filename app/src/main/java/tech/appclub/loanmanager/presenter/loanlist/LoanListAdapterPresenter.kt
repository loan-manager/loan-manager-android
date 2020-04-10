package tech.appclub.loanmanager.presenter.loanlist

import tech.appclub.loanmanager.di.DependencyInjector
import tech.appclub.loanmanager.repo.LoanRepository
import tech.appclub.loanmanager.view.loanlist.LoanViewHolder


class LoanListAdapterPresenter internal constructor(dependencyInjector: DependencyInjector) {

    private val loanRepository: LoanRepository = dependencyInjector.loanRepository()

    fun onBindLoanRowViewAtPosition(position: Int, holder: LoanViewHolder) {
        val loan = loanRepository.loadLoans()[position]
        holder.bind(loan)
    }

    fun getLoanRowsCount(): Int {
        return loanRepository.loadLoans().size
    }

}