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

    @OptIn(DelicateCoroutinesApi::class)
    fun getIncomeRecordLiveData(): LiveData<List<IncomeRecord>> {
        IncomeRecordData.value = listOf()
        GlobalScope.launch {
           // delay(1000)
            val incomeRecordList = incomeRecordRepository.getAllIncomeRecord()
            withContext(Dispatchers.Main) {
                IncomeRecordData.postValue(incomeRecordList)
            }
        }
        return IncomeRecordData
    }
    suspend fun insertIncomeRecord(incomeRecord: IncomeRecord) {
      withContext(Dispatchers.IO){
         incomeRecordRepository.insertIncomeRecord(incomeRecord)
      }
    }

    fun deleteIncomeHead(incomeRecord: IncomeRecord) {
        incomeRecordRepository.deleteIncomeRecord(incomeRecord)
    }
}