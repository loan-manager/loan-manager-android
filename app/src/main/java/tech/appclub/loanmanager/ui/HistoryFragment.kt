package tech.appclub.loanmanager.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import tech.appclub.loanmanager.R
import tech.appclub.loanmanager.adapters.AllLoanRecyclerAdapter
import tech.appclub.loanmanager.adapters.PaidLoanRecyclerAdapter
import tech.appclub.loanmanager.databinding.FragmentHistoryBinding
import tech.appclub.loanmanager.viewmodel.LoanViewModel

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loanViewModel = ViewModelProvider(requireActivity()).get(LoanViewModel::class.java)
        loanViewModel.totalLoans.observe(requireActivity(), Observer {
            this.binding.allRecyclerView.adapter = AllLoanRecyclerAdapter(it, loanViewModel)
        })
    }

}
