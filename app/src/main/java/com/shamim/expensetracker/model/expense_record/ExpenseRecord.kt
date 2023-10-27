package com.shamim.expensetracker.model.expense_record

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shamim.expensetracker.local_db.TableName

@Entity(tableName = TableName.EXPENSE_RECORD)
data class ExpenseRecord(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0,
    val expenseCategoryID: Int? = null,
    val categoryName: String? = null,
    val amount: String? = null,
    val remark: String? = null,
    val dateTime: String? = null,
)
