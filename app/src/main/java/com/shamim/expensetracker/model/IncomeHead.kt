package com.shamim.expensetracker.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shamim.expensetracker.repository.local_db.TableName

@Entity(tableName = TableName.INCOME_HEAD_ENTITY)
data class IncomeHead(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0,
    val incomeHead: String? = null,
    val status: Boolean? = null
)
