package com.shamim.expensetracker.ui.expense

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shamim.expensetracker.R
import com.shamim.expensetracker.databinding.FragmentExpenseBinding
import com.shamim.expensetracker.model.expense_record.ExpenseRecord
import com.shamim.expensetracker.model.income_record.IncomeRecord
import com.shamim.expensetracker.ui.adapter.ExpenseRecordAdapter
import com.shamim.expensetracker.ui.adapter.IncomeRecordAdapter
import com.shamim.expensetracker.view_model.ExpenseHeadViewModel
import com.shamim.expensetracker.view_model.ExpenseRecordViewModel
import com.shamim.expensetracker.view_model.IncomeHeadViewModel
import com.shamim.expensetracker.view_model.IncomeRecordViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExpenseFragment : Fragment() {

    private var _binding: FragmentExpenseBinding? = null

    private lateinit var expenseHeadViewModel: ExpenseHeadViewModel
    private lateinit var expenseRecordViewModel: ExpenseRecordViewModel

    private var totalAmount = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentExpenseBinding.inflate(inflater, container, false)

        expenseHeadViewModel = ViewModelProvider(this)[ExpenseHeadViewModel::class.java]
        expenseRecordViewModel = ViewModelProvider(this)[ExpenseRecordViewModel::class.java]


        _binding!!.addBtn.setOnClickListener {
            findNavController().navigate(R.id.expense_to_addExpenseFragment)
        }

        _binding!!.recyclerview.layoutManager = LinearLayoutManager(requireContext())

        dataShow()

        return _binding!!.root
    }


    private fun dataShow() {
        val data = ArrayList<ExpenseRecord>()
        expenseRecordViewModel.getExpenseRecordLiveData()
            .observe(viewLifecycleOwner) { expenseRecords ->

                if (expenseRecords != null) {
                    totalAmount = 0
                    expenseRecords.forEach {incomeRecord ->
                        totalAmount += incomeRecord.amount!!.toInt()
                    }
                    data.addAll(expenseRecords)
                    val adapter = ExpenseRecordAdapter(data)
                    _binding!!.recyclerview.adapter = adapter
                    Log.d("data", data.toString())

                    _binding!!.tvTotalAmount.text = "Total Expense : $totalAmount"
                }

            }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}