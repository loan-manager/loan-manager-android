package tech.appclub.loanmanager.ui

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.SimpleItemAnimator
import tech.appclub.loanmanager.MainActivity
import tech.appclub.loanmanager.MainActivity.Companion.LOG_TAG
import tech.appclub.loanmanager.R
import tech.appclub.loanmanager.adapters.LoanRecyclerAdapter
import tech.appclub.loanmanager.data.Loan
import tech.appclub.loanmanager.databinding.FragmentHomeBinding
import tech.appclub.loanmanager.utils.Constants
import tech.appclub.loanmanager.viewmodel.LoanViewModel

class HomeFragment : Fragment(), LoanRecyclerAdapter.LoanClickListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var loanViewModel: LoanViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val preferences =
            requireActivity().getSharedPreferences(Constants.COUNTRY_DATA, MODE_PRIVATE)
        val name = preferences.getInt(Constants.COUNTRY_POSITION, -1)
        if (name == -1) {
            findNavController().navigate(R.id.home_to_intro)
        }

        setHasOptionsMenu(true)
        this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        loanViewModel = ViewModelProvider(requireActivity()).get(LoanViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.binding.home = this

        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.show()
        }

        (requireActivity() as MainActivity).binding.bottomNavigationView.visibility = View.VISIBLE

        (this.binding.loanRecyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        this.loanViewModel.unpaidLoans.observe(viewLifecycleOwner, Observer { loans ->
            loans?.let {
                this.binding.loanRecyclerView.adapter = LoanRecyclerAdapter(it, loanViewModel, this)
            }
        })
    }

    fun addLoan() {
        findNavController().navigate(R.id.home_to_add_loan)
    }

    override fun editClickListener(loanId: Int) {
        val action = HomeFragmentDirections.homeToEditFragment(loanId)
        findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
    }

}
