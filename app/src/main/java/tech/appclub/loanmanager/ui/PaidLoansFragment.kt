package tech.appclub.loanmanager.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import tech.appclub.loanmanager.R
import tech.appclub.loanmanager.adapters.PaidLoanRecyclerAdapter
import tech.appclub.loanmanager.databinding.FragmentPaidLoansBinding
import tech.appclub.loanmanager.viewmodel.LoanViewModel

class PaidLoansFragment : Fragment() {

    private lateinit var binding: FragmentPaidLoansBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_paid_loans, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loanViewModel = ViewModelProvider(requireActivity()).get(LoanViewModel::class.java)
        loanViewModel.paidLoans.observe(requireActivity(), Observer { list ->
            this.binding.paidLoanRecyclerView.adapter = PaidLoanRecyclerAdapter(list)
        })
    }

}
