package tech.appclub.loanmanager.presenter

import tech.appclub.loanmanager.DependencyInjector
import tech.appclub.loanmanager.repo.LoanRepository
import tech.appclub.loanmanager.view.LoanRowView

/*
* FILE: LoanListPresenter.kt
* DESC: LOAN LIST PRESENTER WHICH CONTAINS RECYCLER VIEW ADAPTER FUNCTIONS
* VERSION: v1.0
* PRODUCTION READY: FALSE
* AUTHOR: Arslan Mushtaq
* EMAIL: m.arslanmushtaqahmed@gmail.com
* */

class LoanListPresenter internal constructor(dependencyInjector: DependencyInjector) {

    private val loanRepository: LoanRepository = dependencyInjector.loanRepository()

    fun onBindLoanRowViewAtPosition(position: Int, rowView: LoanRowView) {
        val loan = loanRepository.loadLoans()[position]
        rowView.setId(loan.id)
        rowView.setLoanAmount(loan.amount!!)
        rowView.setLoanHolder(loan.holder!!)
        rowView.setPaymentDate(loan.paymentOn!!)
        rowView.setReceiveDate(loan.receivedOn!!)
    }

    fun getLoanRowsCount(): Int {
        return loanRepository.loadLoans().size
    }

}