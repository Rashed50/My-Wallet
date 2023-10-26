package com.shamim.expensetracker.ui.expense

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.shamim.expensetracker.R
import com.shamim.expensetracker.databinding.FragmentExpenseBinding


class ExpenseFragment : Fragment() {

    private var _binding: FragmentExpenseBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentExpenseBinding.inflate(inflater, container, false)

        _binding!!.addBtn.setOnClickListener {
            findNavController().navigate(R.id.expense_to_addExpenseFragment)
        }

        return _binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}