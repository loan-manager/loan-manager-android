package tech.appclub.loanmanager.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import tech.appclub.loanmanager.data.Loan
import tech.appclub.loanmanager.databinding.LoanItemViewBinding
import tech.appclub.loanmanager.utils.DateTimeUtils
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

        holder.todayDate.text = String.format("Today is %s", DateTimeUtils.formatDate(Date()))
        holder.cancelAction.setOnClickListener {
            loans[position].status = 2
            loans[position].paymentOn = Date()
        }

        holder.editAction.setOnClickListener {
            loanClickListener.editClickListener(loans[position])
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
        val todayDate = binding.todayDate

        fun bind(loan: Loan) {
            binding.loan = loan
            binding.executePendingBindings()
        }
    }

    interface LoanClickListener {
        fun editClickListener(loan: Loan)
    }

}