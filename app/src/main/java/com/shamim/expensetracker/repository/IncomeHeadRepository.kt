package com.shamim.expensetracker.repository

import com.shamim.expensetracker.dao.IncomeHeadDao
import com.shamim.expensetracker.model.IncomeHead
import javax.inject.Inject

class IncomeHeadRepository @Inject constructor(
    private val incomeHeadDao: IncomeHeadDao
) {

    fun getAllIncomeHead(): List<IncomeHead> {
        return incomeHeadDao.getIncomeHeadList()
    }

    fun insertIncomeHead(incomeHead: IncomeHead) {
        incomeHeadDao.insertIncomeHead(incomeHead)
    }

    fun deleteIncomeHead(incomeHead: IncomeHead) {
        incomeHeadDao.delete(incomeHead)
    }
    fun deleteAllIncomeHead() {
        incomeHeadDao.deleteIncomeHeadList()
    }
}