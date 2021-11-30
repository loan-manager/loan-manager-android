package tech.appclub.loanmanager.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import tech.appclub.loanmanager.data.LoanDAO
import tech.appclub.loanmanager.db.LoanRoomDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesLoanRoomDatabase(@ApplicationContext context: Context): LoanRoomDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            LoanRoomDatabase::class.java,
            "loan_database"
        ).build();
    }

    @Provides
    @Singleton
    fun providesLoanDao(loanRoomDatabase: LoanRoomDatabase): LoanDAO{
        return loanRoomDatabase.loanDao()
    }
}