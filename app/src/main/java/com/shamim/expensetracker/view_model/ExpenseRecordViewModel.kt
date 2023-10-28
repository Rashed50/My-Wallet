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
    object PreviousMonthData : MutableLiveData<List<ExpenseRecord>>()

    private val _mutableLiveData: MutableLiveData<Int> =MutableLiveData<Int>()
    var data: LiveData<Int> = _mutableLiveData

    fun  setData(data:Int){
        _mutableLiveData.value = data
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getExpenseRecordLiveData(month: String, year: String): LiveData<List<ExpenseRecord>> {
        ExpenseRecordData.value = listOf()
        GlobalScope.launch {
            val expenseRecordList = expenseRecordRepository.getAllExpenseRecord(month, year)
            withContext(Dispatchers.Main) {
                ExpenseRecordData.postValue(expenseRecordList)
            }
        }
        return ExpenseRecordData
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getPreviousMonthLiveData(month: String, year: String): LiveData<List<ExpenseRecord>> {
        PreviousMonthData.value = listOf()
        GlobalScope.launch {
            val expenseRecordList = expenseRecordRepository.getAllExpenseRecord(month, year)
            withContext(Dispatchers.Main) {
                PreviousMonthData.postValue(expenseRecordList)
            }
        }
        return PreviousMonthData
    }

    suspend fun insertExpenseRecord(expenseRecord: ExpenseRecord) {
        withContext(Dispatchers.IO) {
            expenseRecordRepository.insertExpenseRecord(expenseRecord)
        }
    }

    suspend fun deleteItem(id:Int){
        withContext(Dispatchers.IO) {
            expenseRecordRepository.deleteExpenseRecord(id)
        }
    }
    fun deleteExpense(expenseRecord: ExpenseRecord) {
        expenseRecordRepository.deleteExpenseRecord(expenseRecord)
    }
}