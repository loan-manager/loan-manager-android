package tech.appclub.loanmanager.viewholders.loanlist

import androidx.recyclerview.widget.RecyclerView
import tech.appclub.loanmanager.data.Loan
import tech.appclub.loanmanager.databinding.LoanItemViewBinding
import tech.appclub.loanmanager.view.loanlist.LoanRowView

class LoanViewHolder internal constructor(
    private val binding: LoanItemViewBinding
) : RecyclerView.ViewHolder(binding.root),
    LoanRowView {

    override fun bind(loan: Loan) {
        binding.loan = loan;
        binding.executePendingBindings()
    }

}