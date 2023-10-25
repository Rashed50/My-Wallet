package com.shamim.expensetracker.repository

import com.shamim.expensetracker.dao.IncomeHeadDao
import com.shamim.expensetracker.dao.IncomeRecordDao
import com.shamim.expensetracker.model.IncomeHead
import com.shamim.expensetracker.model.income_record.IncomeRecord
import javax.inject.Inject

class IncomeRecordRepository @Inject constructor(
    private val incomeRecordDao: IncomeRecordDao
) {

    fun getAllIncomeRecord(): List<IncomeRecord> {
        return incomeRecordDao.getIncomeRecordList()
    }

    fun insertIncomeRecord(incomeRecord: IncomeRecord) {
        incomeRecordDao.insertIncomeRecord(incomeRecord)
    }

    fun deleteIncomeRecord(incomeRecord: IncomeRecord) {
        incomeRecordDao.delete(incomeRecord)
    }
    fun deleteAllIncomeRecord() {
        incomeRecordDao.deleteIncomeRecordList()
    }
}