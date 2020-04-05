package tech.appclub.loanmanager.contracts

import tech.appclub.loanmanager.presenter.BasePresenter
import tech.appclub.loanmanager.presenter.loanlist.LoanListPresenter
import tech.appclub.loanmanager.view.BaseView

interface MainContract {

    interface Presenter : BasePresenter {
        fun onViewCreated()
        fun onAddLoan()
    }

    interface View : BaseView<Presenter> {
        fun displayLoans(loanListPresenter: LoanListPresenter)
    }

}