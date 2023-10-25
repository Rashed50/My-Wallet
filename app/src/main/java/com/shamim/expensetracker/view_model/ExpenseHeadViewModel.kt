package com.shamim.expensetracker.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shamim.expensetracker.model.ExpenseHead
import com.shamim.expensetracker.repository.ExpenseHeadRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ExpenseHeadViewModel @Inject constructor(
    private val expenseHeadRepository: ExpenseHeadRepository,
) : ViewModel() {


    object ExpenseHeadData : MutableLiveData<List<ExpenseHead>>()

    suspend fun getAllExpenseHead(): List<ExpenseHead> = withContext(Dispatchers.IO) {
        return@withContext expenseHeadRepository.getAllExpenseHead()
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getExpenseLiveData(): LiveData<List<ExpenseHead>> {
        ExpenseHeadData.value = listOf()
        GlobalScope.launch {
            val incomeHeadList = expenseHeadRepository.getAllExpenseHead()
            withContext(Dispatchers.Main) {
                ExpenseHeadData.postValue(incomeHeadList)
            }
        }
        return ExpenseHeadData
    }

    suspend fun insertExpenseHead(incomeHead: ExpenseHead) {
        withContext(Dispatchers.IO) {
            expenseHeadRepository.insertExpenseHead(incomeHead)
        }
    }

    fun deleteExpense(incomeHead: ExpenseHead) {
        expenseHeadRepository.deleteExpenseHead(incomeHead)
    }
}