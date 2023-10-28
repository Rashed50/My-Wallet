package com.shamim.expensetracker.ui.adapter

import com.shamim.expensetracker.model.expense_record.ExpenseRecord
import com.shamim.expensetracker.model.income_record.IncomeRecord

interface DeleteExpenseRecord {
    fun deleteRecord(expenseRecord: ExpenseRecord)
}