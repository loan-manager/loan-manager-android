package tech.appclub.loanmanager.adapters

import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import tech.appclub.loanmanager.R
import tech.appclub.loanmanager.data.Loan
import tech.appclub.loanmanager.databinding.LoanItemViewBinding
import tech.appclub.loanmanager.viewmodel.LoanViewModel
import java.util.*

class LoanRecyclerAdapter internal constructor(
    private val loans: List<Loan> = emptyList(),
    private val loanViewModel: LoanViewModel,
    private val loanClickListener: LoanClickListener
) : RecyclerView.Adapter<LoanRecyclerAdapter.LoanViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoanViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = LoanItemViewBinding.inflate(layoutInflater, parent, false)
        return LoanViewHolder(itemBinding)
    }

    override fun getItemCount() = loans.size

    override fun onBindViewHolder(holder: LoanViewHolder, position: Int) {

        holder.bind(loans[position])
        holder.cancelAction.setOnClickListener {
            showWarningDialog(it, position)
        }

        holder.editAction.setOnClickListener {
            loanClickListener.editClickListener(loans[position].id!!)
        }

        holder.paidAction.setOnClickListener {
            loans[position].status = 1
            loans[position].paymentOn = Date()
            loanViewModel.updateLoan(loans[position])
        }

    }

    inner class LoanViewHolder(private val binding: LoanItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val cancelAction = binding.cancelLoanAction
        val editAction = binding.editLoanAction
        val paidAction = binding.paidLoanAction

        fun bind(loan: Loan) {
            binding.loan = loan
            binding.executePendingBindings()
        }
    }

    interface LoanClickListener {
        fun editClickListener(loanId: Int)
    }

    private fun showWarningDialog(view: View, position: Int) {
        MaterialAlertDialogBuilder(view.context)
            .setTitle("Cancel the Loan")
            .setIcon(R.drawable.ic_warning)
            .setMessage("Are you sure, you want to cancel the loan? Cancelling a loan means the loan is unpaid.")
            .setPositiveButton("Cancel") { _, _ ->
                loans[position].status = 2
                loans[position].paymentOn = Date()
                loanViewModel.updateLoan(loans[position])
            }
            .setNegativeButton("Don't Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

}