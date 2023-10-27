package com.shamim.expensetracker.ui.income

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shamim.expensetracker.R
import com.shamim.expensetracker.databinding.FragmentIncomeBinding
import com.shamim.expensetracker.model.IncomeHead
import com.shamim.expensetracker.model.income_record.IncomeRecord
import com.shamim.expensetracker.ui.adapter.IncomeHeadAdapter
import com.shamim.expensetracker.ui.adapter.IncomeRecordAdapter
import com.shamim.expensetracker.view_model.IncomeHeadViewModel
import com.shamim.expensetracker.view_model.IncomeRecordViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.random.Random

@AndroidEntryPoint
class IncomeFragment : Fragment() {

    private lateinit var _binding: FragmentIncomeBinding
    private lateinit var incomeHeadViewModel: IncomeHeadViewModel
    private lateinit var incomeRecordViewModel: IncomeRecordViewModel

    private var totalAmount = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        incomeHeadViewModel = ViewModelProvider(this)[IncomeHeadViewModel::class.java]
        incomeRecordViewModel = ViewModelProvider(this)[IncomeRecordViewModel::class.java]

        _binding = FragmentIncomeBinding.inflate(inflater, container, false)

        _binding.addBtn.setOnClickListener {
            findNavController().navigate(R.id.income_to_addIncomeFragment)
        }

        _binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())

        dataShow()

        return _binding.root
    }


    private fun dataShow() {
        val data = ArrayList<IncomeRecord>()
        incomeRecordViewModel.getIncomeRecordLiveData()
            .observe(viewLifecycleOwner) { incomeRecords ->

                if (incomeRecords != null) {
                    totalAmount = 0
                    incomeRecords.forEach {incomeRecord ->
                        totalAmount += incomeRecord.amount!!.toInt()
                    }
                    data.addAll(incomeRecords)
                    val adapter = IncomeRecordAdapter(data)
                    _binding.recyclerview.adapter = adapter
                    Log.d("data", data.toString())
                    Log.d("data",totalAmount.toString())

                    _binding.tvTotalAmount.text = "Total Income : $totalAmount"
                }
            }

    }
}