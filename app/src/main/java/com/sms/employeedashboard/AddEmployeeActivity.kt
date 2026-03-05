package com.sms.employeedashboard

import android.os.Bundle
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sms.employeedashboard.data.Employee
import com.sms.employeedashboard.data.EmployeeViewModel

class AddEmployeeActivity : AppCompatActivity() {

    private lateinit var employeeViewModel: EmployeeViewModel

    private val departments = arrayOf(
        "Select Department",
        "Software Development",
        "UI/UX Design",
        "Testing",
        "HR",
        "Marketing"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_employee)

        employeeViewModel = ViewModelProvider(this)[EmployeeViewModel::class.java]

        val firstName = findViewById<EditText>(R.id.etFirstName)
        val lastName = findViewById<EditText>(R.id.etLastName)
        val phone = findViewById<EditText>(R.id.etPhone)
        val email = findViewById<EditText>(R.id.etEmail)
        val designation = findViewById<EditText>(R.id.etDesignation)
        val departmentSpinner = findViewById<Spinner>(R.id.spDepartment)
        val saveBtn = findViewById<Button>(R.id.btnSave)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, departments)
        departmentSpinner.adapter = adapter

        saveBtn.setOnClickListener {
            val fName = firstName.text.toString().trim()
            val lName = lastName.text.toString().trim()
            val phoneText = phone.text.toString().trim()
            val emailText = email.text.toString().trim()
            val departmentText = departmentSpinner.selectedItem.toString()
            val designationText = designation.text.toString().trim()

            if (fName.isEmpty()) {
                firstName.error = "Required"
                return@setOnClickListener
            }

            if (lName.isEmpty()) {
                lastName.error = "Required"
                return@setOnClickListener
            }

            if (!phoneText.matches(Regex("^[6-9][0-9]{9}$"))) {
                phone.error = "Enter valid phone number"
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
                email.error = "Invalid Email"
                return@setOnClickListener
            }

            if (departmentText == "Select Department") {
                Toast.makeText(this, "Please select department", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (designationText.isEmpty()) {
                designation.error = "Required"
                return@setOnClickListener
            }

            val employee = Employee(
                firstName = fName,
                lastName = lName,
                phone = phoneText,
                email = emailText,
                department = departmentText,
                designation = designationText
            )

            employeeViewModel.addEmployee(employee)
            Toast.makeText(this, "Employee Added", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}