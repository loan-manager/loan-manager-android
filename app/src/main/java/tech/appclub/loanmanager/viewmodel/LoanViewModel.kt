package tech.appclub.loanmanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tech.appclub.loanmanager.data.Loan
import tech.appclub.loanmanager.db.LoanRoomDatabase
import tech.appclub.loanmanager.repo.LoanRepository
import javax.inject.Inject

@HiltViewModel
class LoanViewModel @Inject constructor(
    private val repository: LoanRepository
) : ViewModel() {

    val unpaidLoans: LiveData<List<Loan>> = repository.unpaidLoans
    val activeLoans: LiveData<List<Loan>> = repository.activeLoans
    val historyLoans: LiveData<List<Loan>> = repository.historyLoans
    val paidLoans: LiveData<List<Loan>> = repository.paidLoans
    val unpaidLoanCount: LiveData<Int> = repository.unpaidLoanCount
    val unpaidLoanGrandTotal: LiveData<Double> = repository.unpaidLoanGrandTotal
    val paidLoanCount: LiveData<Int> = repository.paidLoanCount
    val paidLoanGrandTotal: LiveData<Double> = repository.paidLoanGrandTotal
    val allLoanCount: LiveData<Int> = repository.allLoanCount

    fun insert(loan: Loan) = viewModelScope.launch {
        repository.insert(loan)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun deleteAllPaidLoans() = viewModelScope.launch {
        repository.deleteAllPaidLoans()
    }

    fun deleteAllUnPaidLoans() = viewModelScope.launch {
        repository.deleteUnPaidLoans()
    }

    fun deleteHistory() = viewModelScope.launch {
        repository.deleteHistory()
    }

//    fun deleteLoan(loan: Loan) = viewModelScope.launch {
//        repository.deleteLoan(loan)
//    }


    fun updateLoan(loan: Loan) = viewModelScope.launch {
        repository.updateLoan(loan)
    }

    fun updateCurrency(code: String, currency: String, country: String, position: Int) =
        viewModelScope.launch {
            repository.updateCurrency(code, currency, country, position)
        }

//    fun currentLoan(loanId: Int): Loan {
//        return runBlocking {
//            repository.currentLoan(loanId)
//        }
//    }

}