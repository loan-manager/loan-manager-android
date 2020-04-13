package tech.appclub.loanmanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.appclub.loanmanager.data.Loan
import tech.appclub.loanmanager.db.LoanRoomDatabase
import tech.appclub.loanmanager.repo.LoanRepository

class LoanViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: LoanRepository
    val allLoans: LiveData<List<Loan>>
    val totalLoanCount: LiveData<Int>
    val grandLoanAmount: LiveData<Double>

    init {
        val loanDao = LoanRoomDatabase.getDatabase(application).loanDao()
        repository = LoanRepository(loanDao)
        allLoans = repository.allLoans
        totalLoanCount = repository.totalLoanCount
        grandLoanAmount = repository.grandLoanAmount
    }

    fun insert(loan: Loan) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(loan)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }

    fun deleteLoan(loan: Loan) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteLoan(loan)
    }

    fun updateLoan(loan: Loan) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateLoan(loan)
    }

}