package tech.appclub.loanmanager.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import tech.appclub.loanmanager.R
import tech.appclub.loanmanager.adapters.HistoryRecyclerAdapter
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
        loanViewModel.historyLoans.observe(viewLifecycleOwner, Observer {
            binding.historyRecyclerView.adapter = HistoryRecyclerAdapter(it)
            Toast.makeText(requireContext(), it.size.toString(), Toast.LENGTH_SHORT).show()
        })
    }
}