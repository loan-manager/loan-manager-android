package tech.appclub.loanmanager.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import tech.appclub.loanmanager.viewmodel.LoanViewModel
import tech.appclub.loanmanager.R
import tech.appclub.loanmanager.adapters.LoanRecyclerAdapter
import tech.appclub.loanmanager.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

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

        this.loanViewModel.allLoans.observe(viewLifecycleOwner, Observer { loans ->
            loans?.let {
                this.binding.loanRecyclerView.adapter = LoanRecyclerAdapter(it)
            }
        })

        this.binding.addLoan.setOnClickListener {
            findNavController().navigate(R.id.home_to_add_loan)
        }
    }

}
