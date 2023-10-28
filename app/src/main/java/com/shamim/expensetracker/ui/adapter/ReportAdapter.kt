package com.shamim.expensetracker.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View 
import android.view.ViewGroup 
import android.widget.ImageView 
import android.widget.TextView 
import androidx.recyclerview.widget.RecyclerView
import com.shamim.expensetracker.R
import com.shamim.expensetracker.helper.DateTime
import com.shamim.expensetracker.model.ExpenseHead
import com.shamim.expensetracker.model.IncomeHead
import com.shamim.expensetracker.model.ReportModel

class ReportAdapter(private val mList: List<String>) : RecyclerView.Adapter<ReportAdapter.ViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

		val view = LayoutInflater.from(parent.context) 
			.inflate(R.layout.report_item, parent, false)

		return ViewHolder(view)
	}
	@SuppressLint("SetTextI18n")
	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val reportModel = mList[position]
		holder.textView.text = reportModel+" - "+DateTime.getYear()

	}
	override fun getItemCount(): Int { 
		return mList.size 
	}
	class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		val textView: TextView = itemView.findViewById(R.id.tvName)
	} 
}
