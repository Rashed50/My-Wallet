package com.shamim.expensetracker.local_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shamim.expensetracker.dao.ExpenseHeadDao
import com.shamim.expensetracker.dao.ExpenseRecordDao
import com.shamim.expensetracker.dao.IncomeHeadDao
import com.shamim.expensetracker.dao.IncomeRecordDao
import com.shamim.expensetracker.model.ExpenseHead
import com.shamim.expensetracker.model.IncomeHead
import com.shamim.expensetracker.model.expense_record.ExpenseRecord
import com.shamim.expensetracker.model.income_record.IncomeRecord

@Database(

    entities = [IncomeHead::class,ExpenseHead::class,IncomeRecord::class,ExpenseRecord::class],
    version = 1,
    exportSchema = false
)
abstract class MainDatabase : RoomDatabase() {

    abstract fun getIncomeHeadDao(): IncomeHeadDao
    abstract fun getExpenseHeadDao(): ExpenseHeadDao
    abstract fun getIncomeRecordDao(): IncomeRecordDao
    abstract fun getExpenseRecordDao(): ExpenseRecordDao


}