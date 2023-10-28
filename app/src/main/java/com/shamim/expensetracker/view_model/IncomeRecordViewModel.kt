package com.shamim.expensetracker.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shamim.expensetracker.model.IncomeHead
import com.shamim.expensetracker.model.income_record.IncomeRecord
import com.shamim.expensetracker.repository.IncomeRecordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class IncomeRecordViewModel @Inject constructor(
    private val incomeRecordRepository: IncomeRecordRepository
) : ViewModel() {


    object IncomeRecordData: MutableLiveData<List<IncomeRecord>>()
    object PreviousMonthData: MutableLiveData<List<IncomeRecord>>()

    private val _mutableLiveData: MutableLiveData<Int> = MutableLiveData<Int>()
    var data: LiveData<Int> = _mutableLiveData

    fun  setData(data:Int){
        _mutableLiveData.value = data
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getIncomeRecordLiveData(month: String, year: String): LiveData<List<IncomeRecord>> {
        IncomeRecordData.value = listOf()
        GlobalScope.launch {
           // delay(1000)
            val incomeRecordList = incomeRecordRepository.getAllIncomeRecord(month, year)
            withContext(Dispatchers.Main) {
                IncomeRecordData.postValue(incomeRecordList)
            }
        }
        return IncomeRecordData
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getPreviousMonthLiveData(month: String, year: String): LiveData<List<IncomeRecord>> {
        PreviousMonthData.value = listOf()
        GlobalScope.launch {
            // delay(1000)
            val incomeRecordList = incomeRecordRepository.getAllIncomeRecord(month, year)
            withContext(Dispatchers.Main) {
                PreviousMonthData.postValue(incomeRecordList)
            }
        }
        return PreviousMonthData
    }
    suspend fun insertIncomeRecord(incomeRecord: IncomeRecord) {
      withContext(Dispatchers.IO){
         incomeRecordRepository.insertIncomeRecord(incomeRecord)
      }
    }

    suspend fun deleteItem(id:Int){
        withContext(Dispatchers.IO){
            incomeRecordRepository.deleteIncomeRecord(id)
        }
    }

    fun deleteIncomeHead(incomeRecord: IncomeRecord) {
        incomeRecordRepository.deleteIncomeRecord(incomeRecord)
    }
}