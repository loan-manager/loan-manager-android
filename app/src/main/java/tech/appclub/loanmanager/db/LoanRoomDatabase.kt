package tech.appclub.loanmanager.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import tech.appclub.loanmanager.data.Loan
import tech.appclub.loanmanager.data.LoanDAO
import tech.appclub.loanmanager.utils.Converters

@Database(entities = [Loan::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class LoanRoomDatabase : RoomDatabase() {

    abstract fun loanDao(): LoanDAO

    companion object {

        // Singleton prevents multiple instances of database opening at the same time
        @Volatile
        private var INSTANCE: LoanRoomDatabase? = null

        fun getDatabase(context: Context): LoanRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LoanRoomDatabase::class.java,
                    "loan_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}