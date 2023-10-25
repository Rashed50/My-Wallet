package com.shamim.expensetracker.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shamim.expensetracker.model.IncomeHead
import com.shamim.expensetracker.repository.IncomeHeadRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class IncomeHeadViewModel @Inject constructor(
    private val incomeHeadRepository: IncomeHeadRepository,
) : ViewModel() {


    object IncomeHeadData: MutableLiveData<List<IncomeHead>>()

    suspend fun getAllIncomeHead(): List<IncomeHead> = withContext(Dispatchers.IO){
        return@withContext incomeHeadRepository.getAllIncomeHead()
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getIncomeLiveData(): LiveData<List<IncomeHead>> {
        IncomeHeadData.value = listOf()
        GlobalScope.launch {
           // delay(1000)
            val incomeHeadList = incomeHeadRepository.getAllIncomeHead()
            withContext(Dispatchers.Main) {
                IncomeHeadData.postValue(incomeHeadList)
            }
        }
        return IncomeHeadData
    }
    suspend fun insertIncomeHead(incomeHead: IncomeHead) {
      withContext(Dispatchers.IO){
          incomeHeadRepository.insertIncomeHead(incomeHead)
      }
    }

    fun deleteIncomeHead(incomeHead: IncomeHead) {
        incomeHeadRepository.deleteIncomeHead(incomeHead)
    }
}