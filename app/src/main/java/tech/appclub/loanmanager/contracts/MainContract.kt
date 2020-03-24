package tech.appclub.loanmanager.contracts

import tech.appclub.loanmanager.presenter.BasePresenter
import tech.appclub.loanmanager.presenter.LoanListPresenter
import tech.appclub.loanmanager.view.BaseView

/*
* FILE: MainContract.kt (Interface)
* DESC: CONTRACT CLASS ACTING AS A BRIDGE FOR MVP DESIGN PATTERN
* VERSION: v1.0
* PRODUCTION READY: FALSE
* AUTHOR: Arslan Mushtaq
* EMAIL: m.arslanmushtaqahmed@gmail.com
* */

interface MainContract {

    interface Presenter : BasePresenter {
        fun onViewCreated()
        fun onAddLoan()
    }

    interface View : BaseView<Presenter> {
        fun displayLoans(loanListPresenter: LoanListPresenter)
    }

}