package com.shamim.expensetracker.model.income_record

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shamim.expensetracker.local_db.TableName

@Entity(tableName = TableName.INCOME_RECORD)
data class IncomeRecord(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0,
    val incomeCategoryID: Int? = null,
    val categoryName: String? = null,
    val amount: String? = null,
    val remark: String? = null,
    val dateTime: String? = null,
    val month: String? = null,
    val year: String? = null,
)
