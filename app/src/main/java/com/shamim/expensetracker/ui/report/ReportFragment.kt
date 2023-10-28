package com.shamim.expensetracker.ui.report

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.shamim.expensetracker.databinding.FragmentReportBinding
import com.shamim.expensetracker.helper.DateTime
import com.shamim.expensetracker.view_model.ExpenseRecordViewModel
import com.shamim.expensetracker.view_model.IncomeRecordViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel


@AndroidEntryPoint
class ReportFragment : Fragment() {

    private var _binding: FragmentReportBinding? = null
    private lateinit var incomeRecordViewModel: IncomeRecordViewModel
    private lateinit var expenseRecordViewModel: ExpenseRecordViewModel
    private var totalIncomeAmount = 0
    private var totalExpenseAmount = 0
    var pieChart: PieChart? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentReportBinding.inflate(inflater, container, false)

        incomeRecordViewModel = ViewModelProvider(this)[IncomeRecordViewModel::class.java]
        expenseRecordViewModel = ViewModelProvider(this)[ExpenseRecordViewModel::class.java]

        dataShow()

        return _binding!!.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun dataShow() {
        pieChart = _binding!!.piechart

        incomeRecordViewModel.getIncomeRecordLiveData(DateTime.getMonth(), DateTime.getYear())
            .observe(viewLifecycleOwner) { incomeRecords ->

                if (incomeRecords != null) {
                    totalIncomeAmount = 0
                    incomeRecords.forEach { incomeRecord ->
                        totalIncomeAmount += incomeRecord.amount!!.toInt()

                    }
                    pieChart!!.addPieSlice(
                        PieModel(
                            "Income", totalIncomeAmount.toFloat(),
                            Color.GREEN
                        )
                    )

                    _binding!!.totalIncome.text = totalIncomeAmount.toString()
                }
            }

        expenseRecordViewModel.getExpenseRecordLiveData(DateTime.getMonth(),DateTime.getYear())
            .observe(viewLifecycleOwner) { expenseRecords ->

                if (expenseRecords != null) {
                    totalExpenseAmount = 0
                    expenseRecords.forEach {incomeRecord ->
                        totalExpenseAmount += incomeRecord.amount!!.toInt()

                    }
                    pieChart!!.addPieSlice(
                        PieModel(
                            "Expense", totalExpenseAmount.toFloat(),
                            Color.RED
                        )
                    )

                    _binding!!.totalExpense.text = totalExpenseAmount.toString()
                }

            }
        pieChart!!.startAnimation()
    }
}