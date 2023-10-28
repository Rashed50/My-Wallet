package com.shamim.expensetracker.ui.income

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shamim.expensetracker.R
import com.shamim.expensetracker.databinding.FragmentIncomeBinding
import com.shamim.expensetracker.helper.DateTime
import com.shamim.expensetracker.model.income_record.IncomeRecord
import com.shamim.expensetracker.ui.adapter.DeleteIncomeRecord
import com.shamim.expensetracker.ui.adapter.IncomeRecordAdapter
import com.shamim.expensetracker.view_model.IncomeHeadViewModel
import com.shamim.expensetracker.view_model.IncomeRecordViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class IncomeFragment : Fragment(), DeleteIncomeRecord {

    private lateinit var _binding: FragmentIncomeBinding
    private lateinit var incomeHeadViewModel: IncomeHeadViewModel
    private lateinit var incomeRecordViewModel: IncomeRecordViewModel
    private var alertDialog: AlertDialog? = null
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
        incomeRecordViewModel.getIncomeRecordLiveData(DateTime.getMonth(), DateTime.getYear())
            .observe(viewLifecycleOwner) { incomeRecords ->

                if (incomeRecords != null) {
                    totalAmount = 0
                    incomeRecords.forEach { incomeRecord ->
                        totalAmount += incomeRecord.amount!!.toInt()
                    }
                    data.addAll(incomeRecords)
                    val adapter = IncomeRecordAdapter(data, this)
                    _binding.recyclerview.adapter = adapter
                    Log.d("data", data.toString())
                    Log.d("data", totalAmount.toString())

                    _binding.tvTotalAmount.text = "Total Income : $totalAmount"
                }
            }

    }

    override fun deleteRecord(incomeRecord: IncomeRecord) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Delete!!")
        alertDialogBuilder.setMessage("Are you sure you want to delete?")
        alertDialogBuilder.setPositiveButton("Yes") { dialogInterface: DialogInterface, _: Int ->
            lifecycleScope.launch {
                incomeRecordViewModel.deleteItem(incomeRecord.id!!)
                dataShow()
                Toast.makeText(requireContext(), "Successfully Deleted", Toast.LENGTH_SHORT).show()
                dialogInterface.dismiss()
            }

        }
        alertDialogBuilder.setNegativeButton(
            "Cancel"
        ) { dialogInterface: DialogInterface, i: Int ->
            dialogInterface.cancel()
        }

        alertDialog = alertDialogBuilder.create()
        alertDialog?.show()
    }
}