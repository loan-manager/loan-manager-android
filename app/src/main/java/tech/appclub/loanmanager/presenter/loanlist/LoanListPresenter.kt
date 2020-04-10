package tech.appclub.loanmanager.presenter.loanlist

import tech.appclub.loanmanager.contracts.LoanListContract
import tech.appclub.loanmanager.di.DependencyInjector

class LoanListPresenter(
    view: LoanListContract.View,
    private val dependencyInjector: DependencyInjector
) : LoanListContract.Presenter {

    private var view: LoanListContract.View? = view

    override fun onViewCreated() {
        view?.displayLoans(
            LoanListAdapterPresenter(
                dependencyInjector
            )
        )
    }

    override fun onDestroy() {
        this.view = null
    }


}