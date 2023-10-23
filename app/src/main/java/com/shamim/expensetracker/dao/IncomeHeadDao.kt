package com.shamim.expensetracker.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shamim.expensetracker.repository.local_db.TableName
import com.shamim.expensetracker.model.IncomeHead


@Dao
interface IncomeHeadDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIncomeHead(incomeHead: IncomeHead)
    @Query("SELECT * FROM ${TableName.INCOME_HEAD_ENTITY}")
    fun getIncomeHeadList(): List<IncomeHead>

    @Delete
    fun delete(incomeHead: IncomeHead)
    @Query("DELETE FROM ${TableName.INCOME_HEAD_ENTITY}")
    fun deleteIncomeHeadList()
}