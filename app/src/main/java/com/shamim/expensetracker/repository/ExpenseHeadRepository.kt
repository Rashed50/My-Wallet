package com.shamim.expensetracker.repository

import com.shamim.expensetracker.dao.ExpenseHeadDao
import com.shamim.expensetracker.model.ExpenseHead
import javax.inject.Inject

class ExpenseHeadRepository @Inject constructor(
    private val expenseHeadDao: ExpenseHeadDao
) {

    fun getAllExpenseHead(): List<ExpenseHead> {
        return  expenseHeadDao.getExpenseHeadList()
    }

    fun insertExpenseHead(incomeHead: ExpenseHead) {
        expenseHeadDao.insertExpenseHead(incomeHead)
    }

    fun deleteExpenseHead(incomeHead: ExpenseHead) {
        expenseHeadDao.delete(incomeHead)
    }
    fun deleteAllExpenseHead() {
        expenseHeadDao.deleteExpenseHeadList()
    }
}