package tech.appclub.loanmanager.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import tech.appclub.loanmanager.data.Loan
import tech.appclub.loanmanager.data.LoanDAO
import tech.appclub.loanmanager.utils.Converters
import java.util.*

@Database(entities = [Loan::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
public abstract class LoanRoomDatabase : RoomDatabase() {

    abstract fun loanDao(): LoanDAO

    private class LoanDatabaseCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val loanDao = database.loanDao()

                    // Delete all content here
                    loanDao.deleteAll()

                    // Add sample words
                    var loan = Loan(
                        0,
                        "Arslan",
                        123456.78,
                        Date(),
                        Date(),
                        "Pakistani Rupees",
                        "PKR",
                        "Pakistan"
                    )
                    loanDao.insert(loan)
                    loan = Loan(
                        1,
                        "Zeshan",
                        876543.21,
                        Date(),
                        Date(),
                        "Pakistani Rupees",
                        "PKR",
                        "Pakistan"
                    )
                    loanDao.insert(loan)
                    loan = Loan(
                        2,
                        "Arfan",
                        723453.65,
                        Date(),
                        Date(),
                        "Pakistani Rupees",
                        "PKR",
                        "Pakistan"
                    )
                    loanDao.insert(loan)
                }
            }
        }

    }

    companion object {

        // Singleton prevents multiple instances of database opening at the same time
        @Volatile
        private var INSTANCE: LoanRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): LoanRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LoanRoomDatabase::class.java,
                    "loan_database"
                ).addCallback(LoanDatabaseCallback(scope)).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}