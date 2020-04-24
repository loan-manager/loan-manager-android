package tech.appclub.loanmanager.adapters

import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
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
        val isExpanded = loans[position].expanded
        if (isExpanded) {
            TransitionManager.beginDelayedTransition(holder.expandableLayout, AutoTransition())
            holder.expandableLayout.visibility = View.VISIBLE
            holder.expandAction.setImageDrawable(
                ContextCompat.getDrawable(holder.itemView.context, R.drawable.ic_expand_less)
            )
        } else {
            TransitionManager.beginDelayedTransition(holder.expandableLayout, AutoTransition())
            holder.expandableLayout.visibility = View.GONE
            holder.expandAction.setImageDrawable(
                ContextCompat.getDrawable(holder.itemView.context, R.drawable.ic_expand)
            )
        }

        holder.expandAction.setOnClickListener {
            val loan = loans[position]
            loan.expanded = !loan.expanded
            TransitionManager.beginDelayedTransition(holder.bottomActions, AutoTransition())
            notifyItemChanged(position)
        }

        holder.bind(loans[position])
        holder.cancelAction.setOnClickListener {
            loans[position].status = 2
            loans[position].paymentOn = Date()
            loanViewModel.updateLoan(loans[position])
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
        val expandableLayout = binding.expandableLayout
        val expandAction = binding.expandAction
        val bottomActions = binding.bottomActions

        fun bind(loan: Loan) {
            binding.loan = loan
            binding.executePendingBindings()
        }
    }

    interface LoanClickListener {
        fun editClickListener(loanId: Int)
    }

}