package tech.appclub.loanmanager.ui

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import tech.appclub.loanmanager.R
import tech.appclub.loanmanager.adapters.UnpaidLoanRecyclerAdapter
import tech.appclub.loanmanager.databinding.FragmentUnpaidLoansBinding
import tech.appclub.loanmanager.viewmodel.LoanViewModel

class UnpaidLoanFragment : Fragment() {

    private lateinit var loanViewModel: LoanViewModel
    private lateinit var binding: FragmentUnpaidLoansBinding
    private var listSize: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_unpaid_loans, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loanViewModel = ViewModelProvider(requireActivity()).get(LoanViewModel::class.java)
        loanViewModel.unpaidLoans.observe(requireActivity(), Observer { list ->
            if (list.isEmpty()) {
                this.binding.emptyMsg.visibility = View.VISIBLE
            } else {
                this.binding.emptyMsg.visibility = View.GONE
            }
            this.binding.allRecyclerView.adapter = UnpaidLoanRecyclerAdapter(list)
            listSize = list.size
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.general_menu, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        requireActivity().invalidateOptionsMenu()
        menu.findItem(R.id.action_delete_all).isVisible = listSize != 0
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete_all -> {
                showDeleteDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showDeleteDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Delete Unpaid Loans")
            .setIcon(R.drawable.ic_warning)
            .setMessage("Are you sure, you want to delete all the unpaid loans?")
            .setPositiveButton("Yes") { dialog, _ ->
                loanViewModel.deleteAllUnPaidLoans()
                dialog.dismiss()
            }
            .setNegativeButton(android.R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

}
