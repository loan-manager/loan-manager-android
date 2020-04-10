package tech.appclub.loanmanager.contracts

import tech.appclub.loanmanager.data.CountryData

interface CountryListContract {

    interface Model {

        interface OnFinishedListener {
            fun onFinished(data: List<CountryData>)
        }

        fun getCountryList(onFinishedListener: OnFinishedListener)
    }

    interface View {

        fun showProgress()
        fun hideProgress()
        fun setCountryToSpinner(countries: List<CountryData>)
    }

    interface Presenter {

        fun onDestroy();
        fun requestDataFromServer()
    }

}