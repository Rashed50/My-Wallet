package com.shamim.expensetracker.repository

import com.shamim.expensetracker.dao.ExpenseHeadDao
import com.shamim.expensetracker.dao.ExpenseRecordDao
import com.shamim.expensetracker.model.ExpenseHead
import com.shamim.expensetracker.model.expense_record.ExpenseRecord
import javax.inject.Inject

class ExpenseRecordRepository @Inject constructor(
    private val expenseRecordDao: ExpenseRecordDao
) {

    fun getAllExpenseRecord(month: String, year: String): List<ExpenseRecord> {
        return expenseRecordDao.getExpenseRecordList(month,year)
    }

    fun insertExpenseRecord(expenseRecord: ExpenseRecord) {
        expenseRecordDao.insertExpenseRecord(expenseRecord)
    }

    fun deleteExpenseRecord(expenseRecord: ExpenseRecord) {
        expenseRecordDao.delete(expenseRecord)
    }
    fun deleteExpenseRecord(id:Int) {
        expenseRecordDao.deleteExpenseRecord(id)
    }
    fun deleteAllExpenseRecordList() {
        expenseRecordDao.deleteExpenseRecordList()
    }
}