package com.shamim.expensetracker.ui.expense

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.shamim.expensetracker.databinding.FragmentAddExpenseBinding
import com.shamim.expensetracker.helper.DateTime
import com.shamim.expensetracker.model.expense_record.ExpenseRecord
import com.shamim.expensetracker.view_model.ExpenseHeadViewModel
import com.shamim.expensetracker.view_model.ExpenseRecordViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class AddExpenseFragment : Fragment() {

    private lateinit var binding: FragmentAddExpenseBinding
    private val calendar = Calendar.getInstance()
    private lateinit var expenseRecordViewModel: ExpenseRecordViewModel
    private lateinit var expenseHeadViewModel: ExpenseHeadViewModel
    private var date = ""

    private val expenseCategoryName = ArrayList<String>()
    private val expenseCategoryId = ArrayList<Int>()
    private var categoryId = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAddExpenseBinding.inflate(inflater, container, false)

        expenseRecordViewModel = ViewModelProvider(this)[ExpenseRecordViewModel::class.java]
        expenseHeadViewModel = ViewModelProvider(this)[ExpenseHeadViewModel::class.java]

        val dataList = listOf<String>("Test 1", "Test 2", "Test 3", "Test 4")

        expenseHeadViewModel.getExpenseLiveData()
            .observe(viewLifecycleOwner) { incomeRecords ->
                incomeRecords?.forEach { incomeHead ->
                    expenseCategoryName.addAll(listOf(incomeHead.incomeHead!!))
                    expenseCategoryId.addAll(listOf(incomeHead.id!!))
                }
                binding.spnMySpinner.setItems(expenseCategoryName.toTypedArray())
            }

        binding.spnMySpinner.setItems(dataList.toTypedArray())

        binding.spnMySpinner.setOnItemClickListener { position ->
            categoryId = expenseCategoryId[position]
        }
        binding.date.setOnClickListener {
            showDatePicker()
        }

        binding.saveBtn.setOnClickListener {
            addData()
        }
        return binding.root
    }

    private fun addData() {
        val name = binding.spnMySpinner.text.toString()
        val amount = binding.amount.text.toString()
        val remark = binding.remark.text.toString()

        if (name.isEmpty()) {
            massage("Please Select Category")
        } else if (amount.isEmpty()) {
            massage("Please Enter Amount")
        } else if (date.isEmpty()) {
            massage("Please Select Date")
        } else {

            val record = ExpenseRecord(
                null, categoryId, name, amount, remark, date,
                DateTime.getMonth(),
                DateTime.getYear()
            )
            lifecycleScope.launch {
                expenseRecordViewModel.insertExpenseRecord(record)
                Toast.makeText(requireContext(), "Data Save Successfully", Toast.LENGTH_SHORT)
                    .show()
                delay(1000)
                findNavController().popBackStack()
            }
        }
    }

    private fun massage(massage: String) {
        Toast.makeText(requireContext(), massage, Toast.LENGTH_SHORT).show()
    }

    private fun showDatePicker() {
        // Create a DatePickerDialog
        val datePickerDialog = DatePickerDialog(
            requireContext(), { _, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                // Create a new Calendar instance to hold the selected date
                val selectedDate = Calendar.getInstance()
                // Set the selected date using the values received from the DatePicker dialog
                selectedDate.set(year, monthOfYear, dayOfMonth)
                // Create a SimpleDateFormat to format the date as "dd/MM/yyyy"
                val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                // Format the selected date into a string
                val formattedDate = dateFormat.format(selectedDate.time)
                // Update the TextView to display the selected date with the "Selected Date: " prefix
                binding.date.setText(formattedDate)
                date = formattedDate
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        // Show the DatePicker dialog
        datePickerDialog.show()
    }
}

fun now(): String {
    return SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
}