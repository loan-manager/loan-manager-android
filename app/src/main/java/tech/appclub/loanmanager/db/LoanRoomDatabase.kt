package tech.appclub.loanmanager.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import tech.appclub.loanmanager.data.Loan
import tech.appclub.loanmanager.data.LoanDAO
import tech.appclub.loanmanager.utils.Converters

@Database(entities = [Loan::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class LoanRoomDatabase : RoomDatabase() {

    abstract fun loanDao(): LoanDAO
}