package tech.appclub.loanmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import tech.appclub.loanmanager.R
import tech.appclub.loanmanager.data.Loan
import tech.appclub.loanmanager.databinding.AllLoanItemViewBinding
import tech.appclub.loanmanager.viewmodel.LoanViewModel

class AllLoanRecyclerAdapter internal constructor(
    private val loans: List<Loan> = emptyList(),
    private val loanViewModel: LoanViewModel
) : RecyclerView.Adapter<AllLoanRecyclerAdapter.AllLoanViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllLoanViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = AllLoanItemViewBinding.inflate(layoutInflater, parent, false)
        return AllLoanViewHolder(itemBinding)
    }

    override fun getItemCount() = loans.size

    override fun onBindViewHolder(holder: AllLoanViewHolder, position: Int) {
        holder.bind(loans[position])

        holder.removeAction.setOnClickListener {
            showDeleteDialog(it, position)
        }
    }

    inner class AllLoanViewHolder(private val binding: AllLoanItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val removeAction = binding.removeLoanAction

        fun bind(loan: Loan) {
            binding.loan = loan
            binding.executePendingBindings()
        }
    }

    private fun showDeleteDialog(view: View, position: Int) {
        MaterialAlertDialogBuilder(view.context)
            .setTitle("Delete the Loan")
            .setIcon(R.drawable.ic_delete)
            .setMessage("Are you sure, you want to delete the loan?")
            .setPositiveButton("Yes") { _, _ ->
                loanViewModel.deleteLoan(loans[position])
                notifyItemRemoved(position)
            }
            .setNegativeButton(android.R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}