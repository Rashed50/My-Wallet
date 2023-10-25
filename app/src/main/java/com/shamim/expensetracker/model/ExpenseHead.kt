package com.shamim.expensetracker.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shamim.expensetracker.local_db.TableName

@Entity(tableName = TableName.EXPENSE_HEAD_ENTITY)
data class ExpenseHead(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0,
    val incomeHead: String? = null,
    val status: Boolean? = null
)
