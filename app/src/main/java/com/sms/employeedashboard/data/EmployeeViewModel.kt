package com.sms.employeedashboard.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmployeeViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Employee>>
    val getEmployeeCount: LiveData<Int>
    private val repository: EmployeeRepository

    init {
        val employeeDao = EmployeeDatabase.getDatabase(application).employeeDao()
        repository = EmployeeRepository(employeeDao)
        readAllData = repository.readAllData
        getEmployeeCount = repository.getEmployeeCount
    }

    fun addEmployee(employee: Employee) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addEmployee(employee)
        }
    }

    fun updateEmployee(employee: Employee) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateEmployee(employee)
        }
    }

    fun deleteEmployee(employee: Employee) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteEmployee(employee)
        }
    }
}