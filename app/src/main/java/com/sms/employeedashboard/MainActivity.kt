package com.sms.employeedashboard

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sms.employeedashboard.adapter.EmployeeAdapter
import com.sms.employeedashboard.data.Employee
import com.sms.employeedashboard.data.EmployeeViewModel
import com.sms.employeedashboard.fragments.UpdateEmployeeFragment

class MainActivity : AppCompatActivity(), EmployeeAdapter.OnItemClickListener {

    private lateinit var employeeViewModel: EmployeeViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EmployeeAdapter
    private lateinit var totalEmployeesText: TextView
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        preferences = getSharedPreferences("loginPrefs", MODE_PRIVATE)

        recyclerView = findViewById(R.id.employeelistRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = EmployeeAdapter(this)
        recyclerView.adapter = adapter

        totalEmployeesText = findViewById(R.id.tvTotalEmployees)
        employeeViewModel = ViewModelProvider(this)[EmployeeViewModel::class.java]

        employeeViewModel.readAllData.observe(this) { employees ->
            adapter.setData(employees)
        }

        employeeViewModel.getEmployeeCount.observe(this) { count ->
            totalEmployeesText.text = "Total Employees: $count"
        }

        findViewById<ImageButton>(R.id.form_btn).setOnClickListener {
            startActivity(Intent(this, AddEmployeeActivity::class.java))
        }

        findViewById<ImageButton>(R.id.btnLogout).setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("Are you sure you want to logout?")
            .setPositiveButton("Logout") { _, _ ->
                preferences.edit().clear().apply()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onEditClick(employee: Employee) {
        val fragment = UpdateEmployeeFragment().apply {
            arguments = Bundle().apply {
                putSerializable("employee", employee)
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDeleteClick(employee: Employee) {
        AlertDialog.Builder(this)
            .setTitle("Delete Employee")
            .setMessage("Are you sure you want to delete this employee?")
            .setPositiveButton("Delete") { _, _ ->
                employeeViewModel.deleteEmployee(employee)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}