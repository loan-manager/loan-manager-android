package tech.appclub.loanmanager.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import tech.appclub.loanmanager.R
import tech.appclub.loanmanager.adapters.HistoryRecyclerAdapter
import tech.appclub.loanmanager.databinding.FragmentHistoryBinding
import tech.appclub.loanmanager.viewmodel.LoanViewModel

class HistoryFragment : Fragment() {

    private lateinit var loanViewModel: LoanViewModel
    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_nav_back)
        }

        loanViewModel = ViewModelProvider(requireActivity()).get(LoanViewModel::class.java)
        loanViewModel.historyLoans.observe(viewLifecycleOwner, Observer {
            updateUI(it.isEmpty())
            binding.historyRecyclerView.adapter = HistoryRecyclerAdapter(it)
        })
    }

    private fun updateUI(isEmpty: Boolean) {
        if (isEmpty) {
            binding.emptyMsg.visibility = View.VISIBLE
        } else {
            binding.emptyMsg.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.general_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete_all -> {
                showAlertDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showAlertDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.history_del_title))
            .setMessage(getString(R.string.history_del_msg))
            .setPositiveButton("Yes") { dialog, _ ->
                loanViewModel.deleteHistory()
                dialog.dismiss()
            }
            .setNegativeButton(android.R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }
            .show()

    }
}