package com.shamim.expensetracker.ui.expense

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shamim.expensetracker.R
import com.shamim.expensetracker.databinding.FragmentExpenseBinding
import com.shamim.expensetracker.helper.DateTime
import com.shamim.expensetracker.model.expense_record.ExpenseRecord
import com.shamim.expensetracker.model.income_record.IncomeRecord
import com.shamim.expensetracker.ui.adapter.DeleteExpenseRecord
import com.shamim.expensetracker.ui.adapter.ExpenseRecordAdapter
import com.shamim.expensetracker.ui.adapter.IncomeRecordAdapter
import com.shamim.expensetracker.view_model.ExpenseHeadViewModel
import com.shamim.expensetracker.view_model.ExpenseRecordViewModel
import com.shamim.expensetracker.view_model.IncomeHeadViewModel
import com.shamim.expensetracker.view_model.IncomeRecordViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExpenseFragment : Fragment(),DeleteExpenseRecord {

    private var _binding: FragmentExpenseBinding? = null

    private lateinit var expenseHeadViewModel: ExpenseHeadViewModel
    private lateinit var expenseRecordViewModel: ExpenseRecordViewModel
    private var alertDialog: AlertDialog? = null
    private var totalAmount = 0
    private var previousMonthData = 0
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
        previousMonthData()
        return _binding!!.root
    }


    private fun dataShow() {
        val data = ArrayList<ExpenseRecord>()
        expenseRecordViewModel.getExpenseRecordLiveData(DateTime.getMonth(), DateTime.getYear())
            .observe(viewLifecycleOwner) { expenseRecords ->

                if (expenseRecords != null) {
                    totalAmount = 0
                    expenseRecords.forEach {incomeRecord ->
                        totalAmount += incomeRecord.amount!!.toInt()
                    }
                    data.addAll(expenseRecords)
                    val adapter = ExpenseRecordAdapter(data,this)
                    _binding!!.recyclerview.adapter = adapter
                    Log.d("data", data.toString())

                    _binding!!.tvTotalAmount.text = "$totalAmount"
                }

            }

    }

    private fun previousMonthData(){
        val month = DateTime.getMonth().toInt() -1
        expenseRecordViewModel.getPreviousMonthLiveData(month.toString(), DateTime.getYear())
            .observe(viewLifecycleOwner) { incomeRecords ->

                if (incomeRecords != null) {
                    previousMonthData = 0
                    incomeRecords.forEach { incomeRecord ->
                        previousMonthData += incomeRecord.amount!!.toInt()
                    }
                    Log.d("previousMonthDate", previousMonthData.toString())

                    _binding!!.previousMonth.text = "$previousMonthData"
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun deleteRecord(expenseRecord: ExpenseRecord) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Delete!!")
        alertDialogBuilder.setMessage("Are you sure you want to delete?")
        alertDialogBuilder.setPositiveButton("Yes") { dialogInterface: DialogInterface, _: Int ->
            lifecycleScope.launch {
                expenseRecordViewModel.deleteItem(expenseRecord.id!!)
                dataShow()
                Toast.makeText(requireContext(), "Successfully Deleted", Toast.LENGTH_SHORT).show()
                dialogInterface.dismiss()
            }

        }
        alertDialogBuilder.setNegativeButton(
            "Cancel"
        ) { dialogInterface: DialogInterface, _: Int ->
            dialogInterface.cancel()
        }

        alertDialog = alertDialogBuilder.create()
        alertDialog?.show()
    }


}