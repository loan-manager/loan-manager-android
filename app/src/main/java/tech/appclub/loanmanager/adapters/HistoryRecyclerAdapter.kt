package tech.appclub.loanmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import tech.appclub.loanmanager.R
import tech.appclub.loanmanager.data.Loan
import tech.appclub.loanmanager.databinding.HistoryLoanItemViewBinding
import tech.appclub.loanmanager.databinding.LoanItemViewBinding
import tech.appclub.loanmanager.viewmodel.LoanViewModel
import java.util.*

class HistoryRecyclerAdapter internal constructor(
    private val loans: List<Loan> = emptyList()
) : RecyclerView.Adapter<HistoryRecyclerAdapter.LoanViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoanViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = HistoryLoanItemViewBinding.inflate(layoutInflater, parent, false)
        return LoanViewHolder(itemBinding)
    }

    override fun getItemCount() = loans.size

    override fun onBindViewHolder(holder: LoanViewHolder, position: Int) {
        holder.bind(loans[position])
    }

    inner class LoanViewHolder(private val binding: HistoryLoanItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loan: Loan) {
            binding.loan = loan
            binding.executePendingBindings()
        }
    }

}