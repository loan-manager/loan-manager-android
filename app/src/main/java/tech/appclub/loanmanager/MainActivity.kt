package tech.appclub.loanmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import tech.appclub.loanmanager.adapters.loanlist.LoanRecyclerAdapter
import tech.appclub.loanmanager.contracts.MainContract
import tech.appclub.loanmanager.databinding.ActivityMainBinding
import tech.appclub.loanmanager.di.DependencyInjectorImpl
import tech.appclub.loanmanager.presenter.loanlist.LoanListPresenter
import tech.appclub.loanmanager.presenter.MainPresenter

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setPresenter(MainPresenter(this,
            DependencyInjectorImpl()
        ))
        presenter.onViewCreated()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun displayLoans(loanListPresenter: LoanListPresenter) {
         this.binding.loansRV.adapter =
             LoanRecyclerAdapter(
                 loanListPresenter
             )
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }
}
