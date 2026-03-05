package com.sms.employeedashboard.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sms.employeedashboard.R
import com.sms.employeedashboard.data.Employee

class EmployeeAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    private var employeeList = emptyList<Employee>()

    interface OnItemClickListener {
        fun onEditClick(employee: Employee)
        fun onDeleteClick(employee: Employee)
    }

    class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvName)
        val phone: TextView = itemView.findViewById(R.id.tvPhone)
        val email: TextView = itemView.findViewById(R.id.tvEmail)
        val department: TextView = itemView.findViewById(R.id.tvDepartment)
        val designation: TextView = itemView.findViewById(R.id.tvDesignation)
        val editBtn: ImageButton = itemView.findViewById(R.id.btnEdit)
        val deleteBtn: ImageButton = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_employee, parent, false)
        return EmployeeViewHolder(view)
    }

    override fun getItemCount(): Int = employeeList.size

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val currentItem = employeeList[position]

        holder.apply {
            name.text = "${currentItem.firstName} ${currentItem.lastName}"
            phone.text = currentItem.phone
            email.text = currentItem.email
            department.text = currentItem.department
            designation.text = currentItem.designation

            editBtn.setOnClickListener { listener.onEditClick(currentItem) }
            deleteBtn.setOnClickListener { listener.onDeleteClick(currentItem) }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(employee: List<Employee>) {
        this.employeeList = employee
        notifyDataSetChanged()
    }
}