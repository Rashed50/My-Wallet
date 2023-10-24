package com.shamim.expensetracker.ui.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shamim.expensetracker.R
import com.shamim.expensetracker.databinding.FragmentReportBinding
import com.shamim.expensetracker.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        binding.incomeBtn.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_settings_to_incomeCategoryFragment)
        }
        return binding.root
    }

}