package tech.appclub.loanmanager.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import tech.appclub.loanmanager.R
import tech.appclub.loanmanager.adapters.loanlist.LoanRecyclerAdapter
import tech.appclub.loanmanager.contracts.LoanListContract
import tech.appclub.loanmanager.databinding.FragmentHomeBinding
import tech.appclub.loanmanager.di.DependencyInjectorImpl
import tech.appclub.loanmanager.presenter.loanlist.LoanListPresenter
import tech.appclub.loanmanager.presenter.loanlist.LoanListAdapterPresenter

class HomeFragment : Fragment(), LoanListContract.View {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var presenter: LoanListContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setPresenter(
            LoanListPresenter(
                this,
                DependencyInjectorImpl()
            )
        )
        this.binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home,
            container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.presenter.onViewCreated()

        this.binding.addLoan.setOnClickListener {
            findNavController().navigate(R.id.home_to_add_loan)
        }
    }

    override fun displayLoans(loanListAdapterPresenter: LoanListAdapterPresenter) {
        this.binding.loanRecyclerView.adapter = LoanRecyclerAdapter(loanListAdapterPresenter)
    }

    override fun setPresenter(presenter: LoanListContract.Presenter) {
        this.presenter = presenter
    }

    override fun onDestroy() {
        this.presenter.onDestroy()
        super.onDestroy()
    }
}
