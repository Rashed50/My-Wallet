package com.shamim.expensetracker.ui.income

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.shamim.expensetracker.R
import com.shamim.expensetracker.databinding.FragmentIncomeBinding
import com.shamim.expensetracker.model.IncomeHead
import com.shamim.expensetracker.view_model.IncomeHeadViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.random.Random

@AndroidEntryPoint
class IncomeFragment : Fragment() {

    private var _binding: FragmentIncomeBinding? = null
    private lateinit var incomeHeadViewModel: IncomeHeadViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        incomeHeadViewModel = ViewModelProvider(this)[IncomeHeadViewModel::class.java]

        _binding = FragmentIncomeBinding.inflate(inflater, container, false)

        _binding!!.addBtn.setOnClickListener {
            findNavController().navigate(R.id.income_to_addIncomeFragment)
        }

        return _binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}