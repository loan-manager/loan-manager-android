package tech.appclub.loanmanager.view

interface BaseView<T> {
    fun setPresenter(presenter: T)
}