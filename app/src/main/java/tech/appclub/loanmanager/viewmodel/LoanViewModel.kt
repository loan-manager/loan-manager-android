package tech.appclub.loanmanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import tech.appclub.loanmanager.data.Loan
import tech.appclub.loanmanager.db.LoanRoomDatabase
import tech.appclub.loanmanager.repo.LoanRepository

class LoanViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: LoanRepository
    val unpaidLoans: LiveData<List<Loan>>
    val activeLoans: LiveData<List<Loan>>
    val paidLoans: LiveData<List<Loan>>
    val unpaidLoanCount: LiveData<Int>
    val unpaidLoanGrandTotal: LiveData<Double>
    val paidLoanCount: LiveData<Int>
    val paidLoanGrandTotal: LiveData<Double>
    val allLoanCount: LiveData<Int>

    init {
        val loanDao = LoanRoomDatabase.getDatabase(application).loanDao()
        repository = LoanRepository(loanDao)
        unpaidLoans = repository.unpaidLoans
        activeLoans = repository.activeLoans
        paidLoans = repository.paidLoans
        unpaidLoanCount = repository.unpaidLoanCount
        unpaidLoanGrandTotal = repository.unpaidLoanGrandTotal
        paidLoanCount = repository.paidLoanCount
        paidLoanGrandTotal = repository.paidLoanGrandTotal
        allLoanCount = repository.allLoanCount
    }

    fun insert(loan: Loan) = viewModelScope.launch {
        repository.insert(loan)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun deleteAllPaidLoans() = viewModelScope.launch {
        repository.deleteAllPaidLoans()
    }

    fun deleteLoan(loan: Loan) = viewModelScope.launch {
        repository.deleteLoan(loan)
    }

    fun updateLoan(loan: Loan) = viewModelScope.launch {
        repository.updateLoan(loan)
    }

    fun updateCurrency(code: String, currency: String, country: String, position: Int) =
        viewModelScope.launch {
            repository.updateCurrency(code, currency, country, position)
        }

    fun currentLoan(loanId: Int): Loan {
        return runBlocking {
            repository.currentLoan(loanId)
        }
    }

}