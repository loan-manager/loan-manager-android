package tech.appclub.loanmanager.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.appclub.loanmanager.R
import tech.appclub.loanmanager.presenter.LoanListPresenter
import tech.appclub.loanmanager.viewholders.LoanViewHolder

/*
* FILE: LoanRecyclerAdapter.kt
* DESC: LOAN RECYCLER ADAPTER
* VERSION: v1.0
* PRODUCTION READY: FALSE
* AUTHOR: Arslan Mushtaq
* EMAIL: m.arslanmushtaqahmed@gmail.com
* */

class LoanRecyclerAdapter internal constructor(
    private val loanListPresenter: LoanListPresenter
) : RecyclerView.Adapter<LoanViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoanViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.loan_item_view, parent, false)
        return LoanViewHolder(view)
    }

    override fun getItemCount(): Int {
        return loanListPresenter.getLoanRowsCount()
    }

    override fun onBindViewHolder(holder: LoanViewHolder, position: Int) {
        loanListPresenter.onBindLoanRowViewAtPosition(position, holder)
    }

}