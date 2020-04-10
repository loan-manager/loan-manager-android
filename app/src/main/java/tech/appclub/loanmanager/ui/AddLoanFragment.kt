package tech.appclub.loanmanager.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import tech.appclub.loanmanager.MainActivity.Companion.LOG_TAG
import tech.appclub.loanmanager.R
import tech.appclub.loanmanager.adapters.CurrencySpinnerAdapter
import tech.appclub.loanmanager.contracts.CountryListContract
import tech.appclub.loanmanager.data.CountryData
import tech.appclub.loanmanager.databinding.FragmentAddLoanBinding
import tech.appclub.loanmanager.presenter.CountryListPresenter

class AddLoanFragment : Fragment(), CountryListContract.View {

    private lateinit var presenter: CountryListPresenter
    private lateinit var binding: FragmentAddLoanBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = CountryListPresenter(this, requireContext())
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_loan, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.requestDataFromServer()
    }

    override fun showProgress() {
        Log.d(LOG_TAG, "Showing Progress")
    }

    override fun hideProgress() {
        Log.d(LOG_TAG, "Hiding Progress")
    }

    override fun setCountryToSpinner(countries: List<CountryData>) {
        val adapter = CurrencySpinnerAdapter(requireContext(), countries)
        this.binding.countries.adapter = adapter
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

}
