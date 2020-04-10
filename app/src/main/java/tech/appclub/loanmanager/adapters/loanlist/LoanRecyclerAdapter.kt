package tech.appclub.loanmanager.adapters.loanlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView
import tech.appclub.loanmanager.databinding.LoanItemViewBinding
import tech.appclub.loanmanager.presenter.loanlist.LoanListAdapterPresenter
import tech.appclub.loanmanager.viewholders.loanlist.LoanViewHolderImpl

class LoanRecyclerAdapter internal constructor(
    private val loanListAdapterPresenter: LoanListAdapterPresenter
) : RecyclerView.Adapter<LoanViewHolderImpl>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoanViewHolderImpl {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = LoanItemViewBinding.inflate(layoutInflater, parent, false)
        return LoanViewHolderImpl(
            itemBinding
        )
    }

    override fun getItemCount(): Int {
        return loanListAdapterPresenter.getLoanRowsCount()
    }

    override fun onBindViewHolder(holderImpl: LoanViewHolderImpl, position: Int) {
        loanListAdapterPresenter.onBindLoanRowViewAtPosition(position, holderImpl)
    }

}