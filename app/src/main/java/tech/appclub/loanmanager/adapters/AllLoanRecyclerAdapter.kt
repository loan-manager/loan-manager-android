package tech.appclub.loanmanager.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import tech.appclub.loanmanager.data.Loan
import tech.appclub.loanmanager.databinding.AllLoanItemViewBinding
import tech.appclub.loanmanager.databinding.LoanItemViewBinding
import tech.appclub.loanmanager.utils.DateTimeUtils
import tech.appclub.loanmanager.viewmodel.LoanViewModel
import java.util.*

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
            loanViewModel.deleteLoan(loans[position])
            notifyItemRemoved(position)
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
}