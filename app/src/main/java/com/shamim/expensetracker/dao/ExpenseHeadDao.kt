package com.shamim.expensetracker.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shamim.expensetracker.model.ExpenseHead
import com.shamim.expensetracker.local_db.TableName


@Dao
interface ExpenseHeadDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExpenseHead(incomeHead: ExpenseHead)

    @Query("SELECT * FROM ${TableName.EXPENSE_HEAD_ENTITY}")
    fun getExpenseHeadList(): List<ExpenseHead>

    @Delete
    fun delete(incomeHead: ExpenseHead)
    @Query("DELETE FROM ${TableName.EXPENSE_HEAD_ENTITY}")
    fun deleteExpenseHeadList()
}