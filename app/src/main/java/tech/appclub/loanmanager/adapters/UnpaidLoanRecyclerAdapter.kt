package tech.appclub.loanmanager.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.appclub.loanmanager.data.Loan
import tech.appclub.loanmanager.databinding.UnpaidLoanItemViewBinding

class UnpaidLoanRecyclerAdapter internal constructor(
    private val loans: List<Loan> = emptyList()
) : RecyclerView.Adapter<UnpaidLoanRecyclerAdapter.AllLoanViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllLoanViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = UnpaidLoanItemViewBinding.inflate(layoutInflater, parent, false)
        return AllLoanViewHolder(itemBinding)
    }

    override fun getItemCount() = loans.size

    override fun onBindViewHolder(holder: AllLoanViewHolder, position: Int) {
        holder.bind(loans[position])
    }

    inner class AllLoanViewHolder(private val binding: UnpaidLoanItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loan: Loan) {
            binding.loan = loan
            binding.executePendingBindings()
        }
    }
}