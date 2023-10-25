package com.shamim.expensetracker.repository

import com.shamim.expensetracker.dao.ExpenseHeadDao
import com.shamim.expensetracker.dao.ExpenseRecordDao
import com.shamim.expensetracker.model.ExpenseHead
import com.shamim.expensetracker.model.expense_record.ExpenseRecord
import javax.inject.Inject

class ExpenseRecordRepository @Inject constructor(
    private val expenseRecordDao: ExpenseRecordDao
) {

    fun getAllExpenseRecord(): List<ExpenseRecord> {
        return expenseRecordDao.getExpenseRecordList()
    }

    fun insertExpenseRecord(expenseRecord: ExpenseRecord) {
        expenseRecordDao.insertExpenseRecord(expenseRecord)
    }

    fun deleteExpenseRecord(expenseRecord: ExpenseRecord) {
        expenseRecordDao.delete(expenseRecord)
    }
    fun deleteAllExpenseRecord() {
        expenseRecordDao.deleteExpenseRecordList()
    }
}