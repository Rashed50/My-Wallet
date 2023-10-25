package com.shamim.expensetracker.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shamim.expensetracker.local_db.TableName
import com.shamim.expensetracker.model.IncomeHead
import com.shamim.expensetracker.model.expense_record.ExpenseRecord
import com.shamim.expensetracker.model.income_record.IncomeRecord


@Dao
interface ExpenseRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExpenseRecord(expenseRecord: ExpenseRecord)

    @Query("SELECT * FROM ${TableName.EXPENSE_RECORD}")
    fun getExpenseRecordList(): List<ExpenseRecord>

    @Delete
    fun delete(expenseRecord: ExpenseRecord)
    @Query("DELETE FROM ${TableName.EXPENSE_RECORD}")
    fun deleteExpenseRecordList()
}