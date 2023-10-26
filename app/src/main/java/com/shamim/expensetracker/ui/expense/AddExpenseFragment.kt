package com.shamim.expensetracker.ui.expense

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shamim.expensetracker.R
import com.shamim.expensetracker.databinding.FragmentAddExpenseBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class AddExpenseFragment : Fragment() {

    private lateinit var binding: FragmentAddExpenseBinding
    private val calendar = Calendar.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAddExpenseBinding.inflate(inflater,container,false)

        val dataList = listOf<String>("Test 1","Test 2","Test 3","Test 4",)
        binding.spnMySpinner.setItems(dataList.toTypedArray())

        binding.spnMySpinner.setOnItemClickListener{item->

        }
        binding.date.setOnClickListener {
            showDatePicker()
        }
        return binding.root
    }


    private fun showDatePicker() {
        // Create a DatePickerDialog
        val datePickerDialog = DatePickerDialog(
            requireContext(), {DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
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
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        // Show the DatePicker dialog
        datePickerDialog.show()
    }
}