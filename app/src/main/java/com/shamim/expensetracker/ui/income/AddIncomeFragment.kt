package com.shamim.expensetracker.ui.income

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.shamim.expensetracker.R
import com.shamim.expensetracker.databinding.FragmentAddIncomeBinding
import com.shamim.expensetracker.helper.DateTime
import com.shamim.expensetracker.model.income_record.IncomeRecord
import com.shamim.expensetracker.ui.adapter.IncomeRecordAdapter
import com.shamim.expensetracker.view_model.IncomeHeadViewModel
import com.shamim.expensetracker.view_model.IncomeRecordViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class AddIncomeFragment : Fragment() {

    private lateinit var binding: FragmentAddIncomeBinding
    private val calendar = Calendar.getInstance()

    private  lateinit var incomeRecordViewModel: IncomeRecordViewModel
    private lateinit var incomeHeadViewModel: IncomeHeadViewModel

    private var date = ""

    private val incomeCategoryName = ArrayList<String>()
    private val incomeCategoryId = ArrayList<Int>()
    private var categoryId = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAddIncomeBinding.inflate(inflater,container,false)

        incomeRecordViewModel = ViewModelProvider(this)[IncomeRecordViewModel::class.java]
        incomeHeadViewModel = ViewModelProvider(this)[IncomeHeadViewModel::class.java]


        incomeHeadViewModel.getIncomeLiveData()
            .observe(viewLifecycleOwner) { incomeRecords ->
                incomeRecords?.forEach { incomeHead ->
                    incomeCategoryName.addAll(listOf(incomeHead.incomeHead!!))
                    incomeCategoryId.addAll(listOf(incomeHead.id!!))
                }
                binding.spnMySpinner.setItems(incomeCategoryName.toTypedArray())
            }

        binding.spnMySpinner.setOnItemClickListener{position->
            categoryId = incomeCategoryId[position]
        }

        binding.date.setOnClickListener {
            showDatePicker()
        }
        binding.saveBtn.setOnClickListener {
            addData()
        }
        return binding.root
    }


    private fun addData(){
        val name = binding.spnMySpinner.text.toString()
        val amount = binding.amount.text.toString()
        val remark = binding.remark.text.toString()

        if (name.isEmpty()){
            massage("Please Select Category")
        }
        else if (amount.isEmpty()){
            massage("Please Enter Amount")
        }
        else if (date.isEmpty()){
            massage("Please Select Date")
        }
        else{

            val incomeRecord = IncomeRecord(null,categoryId,name,amount,remark,date, DateTime.getMonth(),DateTime.getYear())

            lifecycleScope.launch {
                incomeRecordViewModel.insertIncomeRecord(incomeRecord)
                Toast.makeText(requireContext(), "Data Save Successfully", Toast.LENGTH_SHORT).show()
                delay(1000)
                findNavController().popBackStack()
            }
        }
    }
    private fun showDatePicker() {
        val datePickerDialog = DatePickerDialog(
            requireContext(), { _, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)
                binding.date.setText(formattedDate)
                date = formattedDate
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun  massage(massage:String) {
        Toast.makeText(requireContext(), massage, Toast.LENGTH_SHORT).show()
    }
}

