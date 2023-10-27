package com.shamim.expensetracker.ui.expense

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.shamim.expensetracker.R
import com.shamim.expensetracker.databinding.FragmentExpenseCategoryBinding
import com.shamim.expensetracker.model.ExpenseHead
import com.shamim.expensetracker.ui.adapter.ExpenseHeadAdapter
import com.shamim.expensetracker.view_model.ExpenseHeadViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExpenseCategoryFragment : Fragment() {

    private lateinit var binding: FragmentExpenseCategoryBinding

    private lateinit var expenseHeadViewModel: ExpenseHeadViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentExpenseCategoryBinding.inflate(inflater,container,false)

        expenseHeadViewModel = ViewModelProvider(this)[ExpenseHeadViewModel::class.java]

        binding.addBtn.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog)
                .create()
            val view = layoutInflater.inflate(R.layout.customview_layout, null)
            val textInputLayout = view.findViewById<TextInputLayout>(R.id.textInputLayout)
            textInputLayout.hint = "Enter Expense Category"
            val name = view.findViewById<TextInputEditText>(R.id.edtCategoryName)
            val saveBnt = view.findViewById<MaterialButton>(R.id.saveBtn)
            builder.setView(view)
            saveBnt.setOnClickListener {

                if (name.text.toString().isNotEmpty()) {
                    val incomeHead = ExpenseHead(null, name.text.toString(), true)
                    lifecycleScope.launch {
                        expenseHeadViewModel.insertExpenseHead(incomeHead)
                    }
                    builder.dismiss()
                    dataShow()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Please Enter Category Name",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
            builder.setCanceledOnTouchOutside(false)
            builder.show()
        }

        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())

        dataShow()

        return binding.root
    }

    private fun dataShow() {
        val data = ArrayList<ExpenseHead>()

        expenseHeadViewModel.getExpenseLiveData().observe(viewLifecycleOwner) { incomeData ->
            data.addAll(incomeData)
            val adapter = ExpenseHeadAdapter(data)
            binding.recyclerview.adapter = adapter
            Log.d("data", data.toString())
        }


    }

}