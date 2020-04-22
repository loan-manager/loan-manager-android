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
    val unpaidLoans: LiveData<List<Loan>>
    val paidLoans: LiveData<List<Loan>>
    val totalLoans: LiveData<List<Loan>>
    val unpaidLoanCount: LiveData<Int>
    val unpaidLoanGrandTotal: LiveData<Double>
    val paidLoanCount: LiveData<Int>
    val paidLoanGrandTotal: LiveData<Double>
    val allLoanCount: LiveData<Int>

    init {
        val loanDao = LoanRoomDatabase.getDatabase(application).loanDao()
        repository = LoanRepository(loanDao)
        unpaidLoans = repository.unpaidLoans
        paidLoans = repository.paidLoans
        totalLoans = repository.totalLoans
        unpaidLoanCount = repository.unpaidLoanCount
        unpaidLoanGrandTotal = repository.unpaidLoanGrandTotal
        paidLoanCount = repository.paidLoanCount
        paidLoanGrandTotal = repository.paidLoanGrandTotal
        allLoanCount = repository.allLoanCount
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

    fun updateCurrency(code: String, currency: String, country: String) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCurrency(code, currency, country)
        }

}