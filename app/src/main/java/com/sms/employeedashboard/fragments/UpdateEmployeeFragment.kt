package com.sms.employeedashboard.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sms.employeedashboard.R
import com.sms.employeedashboard.data.Employee
import com.sms.employeedashboard.data.EmployeeViewModel

class UpdateEmployeeFragment : Fragment() {

    private lateinit var mEmployeeViewModel: EmployeeViewModel
    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var phone: EditText
    private lateinit var email: EditText
    private lateinit var designation: EditText
    private lateinit var departmentSpinner: Spinner
    private lateinit var updateBtn: Button
    private lateinit var currentEmployee: Employee

    private val departments = arrayOf(
        "Software Development",
        "UI/UX Design",
        "Testing",
        "HR",
        "Marketing"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_update_employee, container, false)

        mEmployeeViewModel = ViewModelProvider(this)[EmployeeViewModel::class.java]

        initViews(view)
        setupSpinner()
        loadEmployeeData()

        updateBtn.setOnClickListener { updateEmployee() }

        return view
    }

    private fun initViews(view: View) {
        firstName = view.findViewById(R.id.etFirstName)
        lastName = view.findViewById(R.id.etLastName)
        phone = view.findViewById(R.id.etPhone)
        email = view.findViewById(R.id.etEmail)
        designation = view.findViewById(R.id.etDesignation)
        departmentSpinner = view.findViewById(R.id.spDepartment)
        updateBtn = view.findViewById(R.id.btnUpdate)
    }

    private fun setupSpinner() {
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            departments
        )
        departmentSpinner.adapter = adapter
    }

    private fun loadEmployeeData() {
        arguments?.getSerializable("employee")?.let {
            currentEmployee = it as Employee

            firstName.setText(currentEmployee.firstName)
            lastName.setText(currentEmployee.lastName)
            phone.setText(currentEmployee.phone)
            email.setText(currentEmployee.email)
            designation.setText(currentEmployee.designation)

            val position = departments.indexOf(currentEmployee.department)
            if (position >= 0) departmentSpinner.setSelection(position)
        }
    }

    private fun updateEmployee() {
        val updatedEmployee = Employee(
            id = currentEmployee.id,
            firstName = firstName.text.toString().trim(),
            lastName = lastName.text.toString().trim(),
            phone = phone.text.toString().trim(),
            email = email.text.toString().trim(),
            department = departmentSpinner.selectedItem.toString(),
            designation = designation.text.toString().trim()
        )

        mEmployeeViewModel.updateEmployee(updatedEmployee)
        Toast.makeText(requireContext(), "Employee Updated", Toast.LENGTH_SHORT).show()
        parentFragmentManager.popBackStack()
    }
}