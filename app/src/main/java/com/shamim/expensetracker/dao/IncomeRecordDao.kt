package com.shamim.expensetracker.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shamim.expensetracker.local_db.TableName
import com.shamim.expensetracker.model.IncomeHead
import com.shamim.expensetracker.model.income_record.IncomeRecord


@Dao
interface IncomeRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIncomeRecord(incomeRecord: IncomeRecord)

    @Query("SELECT * FROM ${TableName.INCOME_RECORD}  WHERE month =:month AND year =:year ")
    fun getIncomeRecordList(month:String,year:String): List<IncomeRecord>

    @Delete
    fun delete(incomeRecord: IncomeRecord)
    @Query("DELETE FROM ${TableName.INCOME_RECORD}")
    fun deleteIncomeRecordList()

    @Query("DELETE FROM ${TableName.INCOME_RECORD} WHERE id =:id")
    fun deleteIncomeRecord(id:Int)
}