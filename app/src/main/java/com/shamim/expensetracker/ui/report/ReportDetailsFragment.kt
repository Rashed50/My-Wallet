package com.shamim.expensetracker.ui.report

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shamim.expensetracker.R
import com.shamim.expensetracker.databinding.FragmentReportDetailsBinding
import com.shamim.expensetracker.helper.DateTime
import com.shamim.expensetracker.model.expense_record.ExpenseRecord
import com.shamim.expensetracker.model.income_record.IncomeRecord
import com.shamim.expensetracker.ui.adapter.ExpenseRecordAdapter
import com.shamim.expensetracker.ui.adapter.ExpenseReportAdapter
import com.shamim.expensetracker.ui.adapter.IncomeRecordAdapter
import com.shamim.expensetracker.ui.adapter.IncomeReportAdapter
import com.shamim.expensetracker.view_model.ExpenseRecordViewModel
import com.shamim.expensetracker.view_model.IncomeRecordViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReportDetailsFragment : Fragment() {
    private lateinit var binding: FragmentReportDetailsBinding
    private lateinit var incomeRecordViewModel: IncomeRecordViewModel
    private lateinit var expenseRecordViewModel: ExpenseRecordViewModel
    private var totalAmount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentReportDetailsBinding.inflate(inflater, container, false)

        incomeRecordViewModel = ViewModelProvider(this)[IncomeRecordViewModel::class.java]
        expenseRecordViewModel = ViewModelProvider(this)[ExpenseRecordViewModel::class.java]

        binding.incomeRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.expenseRecyclerview.layoutManager = LinearLayoutManager(requireContext())

        when(this.arguments?.getString("data")){
            "1" -> dataShow("01")
            "2" -> dataShow("02")
            "3" -> dataShow("03")
            "4" -> dataShow("04")
            "5" -> dataShow("05")
            "6" -> dataShow("06")
            "7" -> dataShow("07")
            "8" -> dataShow("08")
            "9" -> dataShow("09")
            "10" -> dataShow("10")
            "11" -> dataShow("11")
            "12" -> dataShow("12")
        }

        inTotalDataShow()
        filter()
        return binding.root
    }


    private fun dataShow(month: String) {
        val data = ArrayList<IncomeRecord>()
        incomeRecordViewModel.getIncomeRecordLiveData(month, DateTime.getYear())
            .observe(viewLifecycleOwner) { incomeRecords ->
                if (incomeRecords != null) {
                    totalAmount = 0
                    incomeRecords.forEach { incomeRecord ->
                        totalAmount += incomeRecord.amount!!.toInt()
                    }
                    incomeRecordViewModel.setData(totalAmount)
                    data.addAll(incomeRecords)
                    val adapter = IncomeReportAdapter(data)
                    binding.incomeRecyclerview.adapter = adapter
                    Log.d("data", data.toString())
                    Log.d("data", totalAmount.toString())

                }
            }

        val expenseData = ArrayList<ExpenseRecord>()
        expenseRecordViewModel.getExpenseRecordLiveData(month, DateTime.getYear())
            .observe(viewLifecycleOwner) { expenseRecords ->

                if (expenseRecords != null) {
                    totalAmount = 0
                    expenseRecords.forEach { incomeRecord ->
                        totalAmount += incomeRecord.amount!!.toInt()
                    }
                    expenseRecordViewModel.setData(totalAmount)
                    expenseData.addAll(expenseRecords)
                    val adapter = ExpenseReportAdapter(expenseData)
                    binding.expenseRecyclerview.adapter = adapter
                    Log.d("data", expenseData.toString())

                }

            }
    }

    @SuppressLint("SetTextI18n")
    private fun inTotalDataShow() {
        var inTotal: Int
        var x: Int
        var y: Int
        incomeRecordViewModel.data.observe(viewLifecycleOwner, Observer { it ->
            x = it
            binding.incomeTv.text = "Total Income : $it"
            expenseRecordViewModel.data.observe(viewLifecycleOwner, Observer {
                y = it
                inTotal = x - y
                binding.expenseTv.text = "Total Expense : $it"
                binding.inTotalTv.text = "Balance : $inTotal"

            })

        })
    }

    private  fun filter(){
        val data = listOf<String>(
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December",
        )
        val adapter = ArrayAdapter(requireContext(),
            R.layout.spinner_item,R.id.tvName, data)
        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val month = position+1
                when(month.toString()){
                    "1" -> dataShow("01")
                    "2" -> dataShow("02")
                    "3" -> dataShow("03")
                    "4" -> dataShow("04")
                    "5" -> dataShow("05")
                    "6" -> dataShow("06")
                    "7" -> dataShow("07")
                    "8" -> dataShow("08")
                    "9" -> dataShow("09")
                    "10" -> dataShow("10")
                    "11" -> dataShow("11")
                    "12" -> dataShow("12")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }
}