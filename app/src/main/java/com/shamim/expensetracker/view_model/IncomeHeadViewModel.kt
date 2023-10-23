package com.shamim.expensetracker.view_model

import androidx.lifecycle.ViewModel
import com.shamim.expensetracker.model.IncomeHead
import com.shamim.expensetracker.repository.IncomeHeadRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class IncomeHeadViewModel @Inject constructor(
    private val incomeHeadRepository: IncomeHeadRepository,
) : ViewModel() {

    fun getAllIncomeHead(): List<IncomeHead> {
        return incomeHeadRepository.getAllIncomeHead()
    }

    suspend fun insertIncomeHead(incomeHead: IncomeHead) {
      withContext(Dispatchers.IO){
          incomeHeadRepository.insertIncomeHead(incomeHead)
      }
    }

    fun deleteUser(incomeHead: IncomeHead) {
        incomeHeadRepository.deleteIncomeHead(incomeHead)
    }
}