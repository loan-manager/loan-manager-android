package tech.appclub.loanmanager.presenter

import tech.appclub.loanmanager.di.DependencyInjector
import tech.appclub.loanmanager.contracts.MainContract
import tech.appclub.loanmanager.presenter.loanlist.LoanListPresenter

class MainPresenter(view: MainContract.View, private val dependencyInjector: DependencyInjector) :
    MainContract.Presenter {

    // private val loanRepository: LoanRepository = dependencyInjector.loanRepository()
    private var view: MainContract.View? = view

    override fun onViewCreated() {
        view?.displayLoans(
            LoanListPresenter(
                dependencyInjector
            )
        )
    }

    override fun onAddLoan() {

    }

    override fun onDestroy() {
        this.view = null
    }


}