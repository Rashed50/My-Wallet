package com.shamim.expensetracker.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shamim.expensetracker.model.expense_record.ExpenseRecord
import com.shamim.expensetracker.repository.ExpenseRecordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ExpenseRecordViewModel @Inject constructor(
    private val expenseRecordRepository: ExpenseRecordRepository
) : ViewModel() {


    object ExpenseRecordData : MutableLiveData<List<ExpenseRecord>>()


    @OptIn(DelicateCoroutinesApi::class)
    fun getExpenseRecordLiveData(): LiveData<List<ExpenseRecord>> {
        ExpenseRecordData.value = listOf()
        GlobalScope.launch {
            val expenseRecordList = expenseRecordRepository.getAllExpenseRecord()
            withContext(Dispatchers.Main) {
                ExpenseRecordData.postValue(expenseRecordList)
            }
        }
        return ExpenseRecordData
    }

    suspend fun insertExpenseHead(expenseRecord: ExpenseRecord) {
        withContext(Dispatchers.IO) {
            expenseRecordRepository.insertExpenseRecord(expenseRecord)
        }
    }

    fun deleteExpense(expenseRecord: ExpenseRecord) {
        expenseRecordRepository.deleteExpenseRecord(expenseRecord)
    }
}