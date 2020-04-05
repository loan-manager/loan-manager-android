package tech.appclub.loanmanager.adapters.loanlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.appclub.loanmanager.databinding.LoanItemViewBinding
import tech.appclub.loanmanager.presenter.loanlist.LoanListPresenter
import tech.appclub.loanmanager.viewholders.loanlist.LoanViewHolder

class LoanRecyclerAdapter internal constructor(
    private val loanListPresenter: LoanListPresenter
) : RecyclerView.Adapter<LoanViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoanViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = LoanItemViewBinding.inflate(layoutInflater, parent, false)
        return LoanViewHolder(
            itemBinding
        )
    }

    override fun getItemCount(): Int {
        return loanListPresenter.getLoanRowsCount()
    }

    override fun onBindViewHolder(holder: LoanViewHolder, position: Int) {
        loanListPresenter.onBindLoanRowViewAtPosition(position, holder)
    }

}