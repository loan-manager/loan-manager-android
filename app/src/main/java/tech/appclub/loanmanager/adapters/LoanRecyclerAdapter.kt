package tech.appclub.loanmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    }

    inner class LoanViewHolder(private val binding: LoanItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val cancelAction = binding.cancelLoanAction
        val editAction = binding.editLoanAction

        fun bind(loan: Loan) {
            binding.loan = loan
            binding.executePendingBindings()
        }
    }

    interface LoanClickListener {
        fun editClickListener(loanId: Int)
    }

    private fun showWarningDialog(view: View, position: Int) {
        val message =
            "Choose an action to perform. Select Unpaid if you're willing to cancel the loan."


        MaterialAlertDialogBuilder(view.context)
            .setTitle("Closing the Loan")
            .setIcon(R.drawable.ic_close_dialog_icon)
            .setMessage(message)
            .setPositiveButton("PAID") { _, _ ->
                loans[position].status = 1
                loans[position].paymentOn = Date()
                loanViewModel.updateLoan(loans[position])
            }
            .setNegativeButton("UNPAID") { _, _ ->
                loans[position].status = 0
                loans[position].paymentOn = Date()
                loanViewModel.updateLoan(loans[position])
            }
            .setNeutralButton("CANCEL") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

}