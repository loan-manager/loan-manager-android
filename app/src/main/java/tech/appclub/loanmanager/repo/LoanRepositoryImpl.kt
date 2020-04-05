package tech.appclub.loanmanager.repo

import tech.appclub.loanmanager.data.Loan
import java.math.BigDecimal
import java.util.*

class LoanRepositoryImpl : LoanRepository {

    override fun loadLoans(): List<Loan> {

        val loan1 = Loan("0", "Arslan Mushtaq", BigDecimal(30_020), Date(), Date())
        val loan2 = Loan("1", "Zeshan Mushtaq", BigDecimal(1_030_208), Date(), Date())
        val loan3 = Loan("2", "Arfan Sohaib", BigDecimal(9_201_632), Date(), Date())

        val sampleLoanList = arrayListOf<Loan>()
        sampleLoanList.add(0, loan1)
        sampleLoanList.add(1, loan2)
        sampleLoanList.add(2, loan3)

        return sampleLoanList
    }

}