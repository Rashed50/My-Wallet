package com.shamim.expensetracker.ui.adapter

import com.shamim.expensetracker.model.income_record.IncomeRecord

interface DeleteIncomeRecord {
    fun deleteRecord(incomeRecord: IncomeRecord)
}