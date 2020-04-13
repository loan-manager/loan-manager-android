package tech.appclub.loanmanager;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

import tech.appclub.loanmanager.data.Loan;
import tech.appclub.loanmanager.data.LoanDAO;
import tech.appclub.loanmanager.db.LoanRoomDatabase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class LoanDatabaseTest {

    /*
     * Swaps the background executor used by the Architecture Components
     * with a different one which executes each task synchronously.
     * */
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private LoanDAO mLoanDAO;
    private LoanRoomDatabase mLoanRoomDatabase;

    @Before
    public void createDatabase() {
        Context context = ApplicationProvider.getApplicationContext();
        mLoanRoomDatabase = Room.inMemoryDatabaseBuilder(context, LoanRoomDatabase.class)
                .allowMainThreadQueries()
                .build();
        mLoanDAO = mLoanRoomDatabase.loanDao();
        Loan loanOne = new Loan();
        loanOne.setHolder("Holder");
        loanOne.setAmount(25.0);
        loanOne.setReceivedOn(new Date());
        loanOne.setPaymentOn(new Date());
        loanOne.setCurrency("Pakistani Rupees");
        loanOne.setCode("PKR");
        loanOne.setCountry("Pakistan");
        mLoanDAO.insert(loanOne);

        Loan loanTwo = new Loan();
        loanTwo.setHolder("Holder");
        loanTwo.setAmount(25.0);
        loanTwo.setReceivedOn(new Date());
        loanTwo.setPaymentOn(new Date());
        loanTwo.setCurrency("Pakistani Rupees");
        loanTwo.setCode("PKR");
        loanTwo.setCountry("Pakistan");
        mLoanDAO.insert(loanTwo);

    }

    @After
    public void closeDatabase() {
        mLoanRoomDatabase.close();
    }

    @Test
    public void insertAndGetLoan() throws Exception {
        List<Loan> allLoans = LiveDataTestUtil.getValue(mLoanDAO.getUnpaidLoans());
        assertEquals(allLoans.get(0).getHolder(), allLoans.get(0).getHolder());
    }

    @Test
    public void deleteAllLoans() throws Exception {
        mLoanDAO.deleteAll();
        List<Loan> allLoans = LiveDataTestUtil.getValue(mLoanDAO.getUnpaidLoans());
        assertEquals(0, allLoans.size());
    }

    @Test
    public void deleteLoan() throws Exception {
        List<Loan> allLoans = LiveDataTestUtil.getValue(mLoanDAO.getUnpaidLoans());
        mLoanDAO.deleteLoan(allLoans.get(1));
        List<Loan> loans = LiveDataTestUtil.getValue(mLoanDAO.getUnpaidLoans());
        assertEquals(1, loans.size());
    }

    @Test
    public void updateLoan() throws Exception {
        List<Loan> allLoans = LiveDataTestUtil.getValue(mLoanDAO.getUnpaidLoans());
        Loan loanOne = allLoans.get(0);
        loanOne.setAmount(50.0);
        mLoanDAO.updateLoan(loanOne);
        List<Loan> allUpdatedLoans = LiveDataTestUtil.getValue(mLoanDAO.getUnpaidLoans());
        assertEquals((Double) 50.0, allUpdatedLoans.get(0).getAmount());
    }

    @Test
    public void testTotalCount() throws Exception {
        List<Loan> allLoans = LiveDataTestUtil.getValue(mLoanDAO.getUnpaidLoans());
        assertEquals(mLoanDAO.totalLoanCount(), allLoans.size());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testTotalLoanAmount() throws Exception {
        List<Loan> allLoans = LiveDataTestUtil.getValue(mLoanDAO.getUnpaidLoans());
        Double one = allLoans.get(0).getAmount();
        Double two = allLoans.get(0).getAmount();
        Double sum = one + two;
        double totalAmount = mLoanDAO.totalLoanAmount();
        assertEquals((Double) totalAmount, sum);
    }

    @Test
    public void emptyTablesInDB() throws Exception {
        List<Loan> allLoans = LiveDataTestUtil.getValue(mLoanDAO.getUnpaidLoans());
        assertTrue(allLoans.size() > 0);
    }

    @Test
    public void idAutoIncrementing() throws Exception {
        List<Loan> allLoans = LiveDataTestUtil.getValue(mLoanDAO.getUnpaidLoans());
        Integer expectedId = 2;
        assertEquals(expectedId, allLoans.get(1).getId());
    }

}
