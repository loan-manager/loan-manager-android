package tech.appclub.loanmanager.viewholders.loanlist

import androidx.recyclerview.widget.RecyclerView
import tech.appclub.loanmanager.data.Loan
import tech.appclub.loanmanager.databinding.LoanItemViewBinding
import tech.appclub.loanmanager.view.loanlist.LoanViewHolder

class LoanViewHolderImpl internal constructor(
    private val binding: LoanItemViewBinding
) : RecyclerView.ViewHolder(binding.root),
    LoanViewHolder {

    override fun bind(loan: Loan) {
        binding.loan = loan;
        binding.executePendingBindings()
    }

}