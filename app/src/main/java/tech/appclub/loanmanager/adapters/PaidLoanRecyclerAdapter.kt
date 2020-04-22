package tech.appclub.loanmanager.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.appclub.loanmanager.data.Loan
import tech.appclub.loanmanager.databinding.PaidLoanItemViewBinding

class PaidLoanRecyclerAdapter internal constructor(
    private val loans: List<Loan> = emptyList()
) : RecyclerView.Adapter<PaidLoanRecyclerAdapter.PaidLoanViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaidLoanViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = PaidLoanItemViewBinding.inflate(layoutInflater, parent, false)
        return PaidLoanViewHolder(itemBinding)
    }

    override fun getItemCount() = loans.size

    override fun onBindViewHolder(holder: PaidLoanViewHolder, position: Int) {
        holder.bind(loans[position])
    }

    inner class PaidLoanViewHolder(private val binding: PaidLoanItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loan: Loan) {
            binding.loan = loan
            binding.executePendingBindings()
        }
    }
}