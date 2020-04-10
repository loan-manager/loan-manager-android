package tech.appclub.loanmanager.presenter

import android.content.Context
import tech.appclub.loanmanager.CountryListModel
import tech.appclub.loanmanager.contracts.CountryListContract
import tech.appclub.loanmanager.data.CountryData

class CountryListPresenter internal constructor(
    private var countryListView: CountryListContract.View? = null,
    context: Context
) : CountryListContract.Presenter,
    CountryListContract.Model.OnFinishedListener {

    private val countryListModel: CountryListContract.Model = CountryListModel(context)

    override fun onFinished(data: List<CountryData>) {
        countryListView?.setCountryToSpinner(data)
        if (countryListView != null) {
            countryListView?.hideProgress()
        }
    }

    override fun onDestroy() {
        this.countryListView = null
    }

    override fun requestDataFromServer() {
        if (countryListView != null) {
            countryListView?.showProgress()
        }
        countryListModel.getCountryList(this)
    }


}