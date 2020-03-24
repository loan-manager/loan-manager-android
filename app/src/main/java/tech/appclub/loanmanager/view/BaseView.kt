package tech.appclub.loanmanager.view

/*
* FILE: BaseView<T>.kt (INTERFACE)
* DESC: A BASE VIEW INTERFACE WHICH SETS A DYNAMIC PRESENTER
* VERSION: v1.0
* PRODUCTION READY: FALSE
* AUTHOR: Arslan Mushtaq
* EMAIL: m.arslanmushtaqahmed@gmail.com
* */

interface BaseView<T> {
    fun setPresenter(presenter: T)
}