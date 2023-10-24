package com.shamim.expensetracker.ui.income

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.shamim.expensetracker.R
import com.shamim.expensetracker.databinding.FragmentIncomeCategoryBinding
import com.shamim.expensetracker.model.IncomeHead
import com.shamim.expensetracker.ui.income.adapter.IncomeHeadAdapter
import com.shamim.expensetracker.view_model.IncomeHeadViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.random.Random

@AndroidEntryPoint
class IncomeCategoryFragment : Fragment() {

    private lateinit var binding: FragmentIncomeCategoryBinding
    private lateinit var incomeHeadViewModel: IncomeHeadViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentIncomeCategoryBinding.inflate(inflater, container, false)

        incomeHeadViewModel = ViewModelProvider(this)[IncomeHeadViewModel::class.java]

        binding.addBtn.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog)
                .create()
            val view = layoutInflater.inflate(R.layout.customview_layout, null)
            val name = view.findViewById<TextInputEditText>(R.id.edtCategoryName)
            val saveBnt = view.findViewById<MaterialButton>(R.id.saveBtn)
            builder.setView(view)
            saveBnt.setOnClickListener {

                if (name.text.toString().isNotEmpty()) {
                    val incomeHead = IncomeHead(null, name.text.toString(), true)
                    lifecycleScope.launch {
                        incomeHeadViewModel.insertIncomeHead(incomeHead)
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
        val data = ArrayList<IncomeHead>()
//        lifecycleScope.launch {
//            val data = ArrayList<IncomeHead>()
//            incomeHeadViewModel.getAllIncomeHead().forEach {income->
//                data.add(income)
//                Log.d("data",income.toString())
//            }
//            val adapter = IncomeHeadAdapter(data)
//            binding.recyclerview.adapter = adapter
//            Log.d("data",data.toString())
//
//        }

        incomeHeadViewModel.getAddressesLiveData().observe(viewLifecycleOwner) { incomeData ->
            data.addAll(incomeData)
            val adapter = IncomeHeadAdapter(data)
            binding.recyclerview.adapter = adapter
            Log.d("data", data.toString())
        }


    }
}