package tech.appclub.loanmanager.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import tech.appclub.loanmanager.MainActivity.Companion.LOG_TAG
import tech.appclub.loanmanager.viewmodel.LoanViewModel
import tech.appclub.loanmanager.R
import tech.appclub.loanmanager.adapters.LoanRecyclerAdapter
import tech.appclub.loanmanager.data.Loan
import tech.appclub.loanmanager.databinding.FragmentHomeBinding
import java.text.DecimalFormat

class HomeFragment : Fragment(), LoanRecyclerAdapter.LoanClickListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var loanViewModel: LoanViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        loanViewModel = ViewModelProvider(requireActivity()).get(LoanViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.loanViewModel.unpaidLoans.observe(viewLifecycleOwner, Observer { loans ->
            loans?.let {
                this.binding.loanRecyclerView.adapter = LoanRecyclerAdapter(it, loanViewModel, this)
            }
        })

        this.loanViewModel.totalLoanCount.observe(viewLifecycleOwner, Observer { count ->
            if (count == 0) {
                this.binding.totalLoans.text = "0"
                return@Observer
            }
            this.binding.totalLoans.text = count.toString()
        })

        this.loanViewModel.grandLoanAmount.observe(viewLifecycleOwner, Observer { grandTotal ->
            if (grandTotal == null) {
                this.binding.grandAmount.text = "0"
                return@Observer
            }
            val decimalFormat = DecimalFormat()
            this.binding.grandAmount.text = String.format("PKR %s", decimalFormat.format(grandTotal))
        })

        this.binding.addLoan.setOnClickListener {
            findNavController().navigate(R.id.home_to_add_loan)
        }
    }

    override fun editClickListener(loan: Loan) {
        Log.d(LOG_TAG, loan.holder!!)
    }

}
