package tech.appclub.loanmanager.contracts

import tech.appclub.loanmanager.presenter.BasePresenter
import tech.appclub.loanmanager.presenter.loanlist.LoanListAdapterPresenter
import tech.appclub.loanmanager.view.BaseView

interface LoanListContract {

    interface Presenter : BasePresenter {
        fun onViewCreated()
    }

    interface View : BaseView<Presenter> {
        fun displayLoans(loanListAdapterPresenter: LoanListAdapterPresenter)
    }

}