package com.shamim.expensetracker.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shamim.expensetracker.R
import com.shamim.expensetracker.model.income_record.IncomeRecord

class IncomeReportAdapter(private val mList: List<IncomeRecord>) : RecyclerView.Adapter<IncomeReportAdapter.ViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

		val view = LayoutInflater.from(parent.context) 
			.inflate(R.layout.report_details_item, parent, false)

		return ViewHolder(view)
	}
	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val incomeRecord = mList[position]
		holder.name.text = incomeRecord.categoryName
		val amount =incomeRecord.amount
		holder.amount.text = "$amount"


	}
	override fun getItemCount(): Int { 
		return mList.size 
	}
	class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		val name: TextView = itemView.findViewById(R.id.tvName)
		val amount: TextView = itemView.findViewById(R.id.tvAmount)


	}
}
