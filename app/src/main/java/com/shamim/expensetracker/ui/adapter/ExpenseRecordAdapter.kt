package com.shamim.expensetracker.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shamim.expensetracker.R
import com.shamim.expensetracker.model.expense_record.ExpenseRecord

class ExpenseRecordAdapter(private val mList: List<ExpenseRecord>,private val deleteExpenseRecord: DeleteExpenseRecord) : RecyclerView.Adapter<ExpenseRecordAdapter.ViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

		val view = LayoutInflater.from(parent.context) 
			.inflate(R.layout.income_record_item, parent, false)

		return ViewHolder(view)
	}
	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val expenseRecord = mList[position]
		holder.name.text = expenseRecord.categoryName
		val amount =expenseRecord.amount
		holder.amount.text = "Amount : $amount"
		holder.remark.text = expenseRecord.remark
		holder.remark.isSelected = true
		holder.date.text = expenseRecord.dateTime
		holder.delete.setOnClickListener {
			deleteExpenseRecord.deleteRecord(expenseRecord)
		}
	}
	override fun getItemCount(): Int { 
		return mList.size 
	}
	class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		val name: TextView = itemView.findViewById(R.id.tvName)
		val amount: TextView = itemView.findViewById(R.id.tvAmount)
		val remark: TextView = itemView.findViewById(R.id.tvRemark)
		val date: TextView = itemView.findViewById(R.id.tvDate)
		val delete: ImageView = itemView.findViewById(R.id.delete)
	}
}
