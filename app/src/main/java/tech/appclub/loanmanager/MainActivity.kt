package tech.appclub.loanmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tech.appclub.loanmanager.adapters.LoanRecyclerAdapter
import tech.appclub.loanmanager.contracts.MainContract
import tech.appclub.loanmanager.databinding.ActivityMainBinding
import tech.appclub.loanmanager.presenter.LoanListPresenter
import tech.appclub.loanmanager.presenter.MainPresenter

/*
* FILE: MainActivity.kt
* DESC: MAIN ACTIVITY
* VERSION: v1.0
* PRODUCTION READY: FALSE
* AUTHOR: Arslan Mushtaq
* EMAIL: m.arslanmushtaqahmed@gmail.com
* */

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setPresenter(MainPresenter(this, DependencyInjectorImpl()))
        presenter.onViewCreated()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun displayLoans(loanListPresenter: LoanListPresenter) {
        this.binding.loansRV.adapter = LoanRecyclerAdapter(loanListPresenter)
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }
}
