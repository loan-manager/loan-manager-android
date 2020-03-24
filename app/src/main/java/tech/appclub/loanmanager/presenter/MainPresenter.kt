package tech.appclub.loanmanager.presenter

import tech.appclub.loanmanager.DependencyInjector
import tech.appclub.loanmanager.contracts.MainContract

/*
* FILE: MainPresenter.kt
* DESC: MAIN ACTIVITY PRESENTER WHICH CONNECTS THE VIEW AND MODEL
* VERSION: v1.0
* PRODUCTION READY: FALSE
* AUTHOR: Arslan Mushtaq
* EMAIL: m.arslanmushtaqahmed@gmail.com
* */

class MainPresenter(view: MainContract.View, private val dependencyInjector: DependencyInjector) :
    MainContract.Presenter {

    // private val loanRepository: LoanRepository = dependencyInjector.loanRepository()
    private var view: MainContract.View? = view

    override fun onViewCreated() {
        view?.displayLoans(LoanListPresenter(dependencyInjector))
    }

    override fun onAddLoan() {

    }

    override fun onDestroy() {
        this.view = null
    }


}