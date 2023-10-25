package com.shamim.expensetracker.local_db

import android.content.Context
import androidx.room.Room
import com.shamim.expensetracker.dao.ExpenseHeadDao
import com.shamim.expensetracker.dao.ExpenseRecordDao
import com.shamim.expensetracker.dao.IncomeHeadDao
import com.shamim.expensetracker.dao.IncomeRecordDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
private const val DB_NAME = "db_name"
@Module
@InstallIn(SingletonComponent::class)
object LocalCashModule {

    @Provides
    @Singleton
    @Named(value = DB_NAME)
    fun provideDatabaseName(): String {
        return "local_db"
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @Named(value = DB_NAME) dbname: String,
        @ApplicationContext context: Context
    ): MainDatabase {
        return Room.databaseBuilder(context, MainDatabase::class.java, dbname)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideIncomeHeadDao(database: MainDatabase): IncomeHeadDao {
        return database.getIncomeHeadDao()
    }

    @Provides
    @Singleton
    fun provideExpenseHeadDao(database: MainDatabase): ExpenseHeadDao {
        return database.getExpenseHeadDao()
    }


    @Provides
    @Singleton
    fun provideIncomeRecordDao(database: MainDatabase): IncomeRecordDao {
        return database.getIncomeRecordDao()
    }

    @Provides
    @Singleton
    fun provideExpenseRecordDao(database: MainDatabase): ExpenseRecordDao {
        return database.getExpenseRecordDao()
    }



}