package tech.appclub.loanmanager

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.appclub.loanmanager.data.Loan
import tech.appclub.loanmanager.db.LoanRoomDatabase
import tech.appclub.loanmanager.repo.LoanRepository

class LoanViewModel(application: Application): AndroidViewModel(application) {

    private val repository: LoanRepository
    val allLoans: LiveData<List<Loan>>

    init {
        val loanDao = LoanRoomDatabase.getDatabase(application, viewModelScope).loanDao()
        repository = LoanRepository(loanDao)
        allLoans = repository.allLoans
    }

    fun insert(loan: Loan) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(loan)
    }

}