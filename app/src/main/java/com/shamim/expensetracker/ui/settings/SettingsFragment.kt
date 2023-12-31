package com.shamim.expensetracker.ui.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.shamim.expensetracker.R
import com.shamim.expensetracker.databinding.FragmentSettingsBinding
import com.shamim.expensetracker.helper.DateTime
import com.shamim.expensetracker.view_model.ExpenseRecordViewModel
import com.shamim.expensetracker.view_model.IncomeRecordViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var incomeRecordViewModel: IncomeRecordViewModel
    private lateinit var expenseRecordViewModel: ExpenseRecordViewModel
    private var totalIncomeAmount = 0
    private var totalExpenseAmount = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        incomeRecordViewModel = ViewModelProvider(this)[IncomeRecordViewModel::class.java]
        expenseRecordViewModel = ViewModelProvider(this)[ExpenseRecordViewModel::class.java]

        binding.incomeBtn.setOnClickListener {
            findNavController().navigate(R.id.settings_to_incomeCategoryFragment)
        }
        binding.expenseBtn.setOnClickListener {
            findNavController().navigate(R.id.settings_to_expenseCategoryFragment)
        }

        dataShow()

        binding.tvPrivacy.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://3iengineers.com/app.privary-policy?fbclid=IwAR0KA3k3YuWXmbA1EZKbEOHpFu-QieE5ubJOXmdIhW-PiT48kwgR28_a9mM")
            )
            startActivity(intent)
        }

//        binding.moreApps.setOnClickListener {
//            val intent = Intent(
//                Intent.ACTION_VIEW,
//                Uri.parse("https://play.google.com/store/apps/developer?id=")
//            )
//            startActivity(intent)
//        }
        return binding.root
    }
    private fun dataShow() {
        incomeRecordViewModel.getIncomeRecordLiveData(DateTime.getMonth(),DateTime.getYear())
            .observe(viewLifecycleOwner) { incomeRecords ->

                if (incomeRecords != null) {
                    totalIncomeAmount = 0
                    incomeRecords.forEach { incomeRecord ->
                        totalIncomeAmount += incomeRecord.amount!!.toInt()
                    }

                    Log.d("data", totalIncomeAmount.toString())

                    binding.totalIncome.text = totalIncomeAmount.toString()
                }
            }

        expenseRecordViewModel.getExpenseRecordLiveData(DateTime.getMonth(), DateTime.getYear())
            .observe(viewLifecycleOwner) { expenseRecords ->

                if (expenseRecords != null) {
                    totalExpenseAmount = 0
                    expenseRecords.forEach {incomeRecord ->
                        totalExpenseAmount += incomeRecord.amount!!.toInt()
                    }

                    binding.totalExpense.text = totalExpenseAmount.toString()
                }

            }
    }
}