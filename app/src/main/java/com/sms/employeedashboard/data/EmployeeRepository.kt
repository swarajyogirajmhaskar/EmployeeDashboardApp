package com.sms.employeedashboard.data

import androidx.lifecycle.LiveData

class EmployeeRepository(private val employeeDao: EmployeeDao) {

    val readAllData: LiveData<List<Employee>> = employeeDao.readAllData()
    val getEmployeeCount: LiveData<Int> = employeeDao.getEmployeeCount()

    suspend fun addEmployee(employee: Employee) {
        employeeDao.addEmployee(employee)
    }

    suspend fun updateEmployee(employee: Employee) {
        employeeDao.updateEmployee(employee)
    }

    suspend fun deleteEmployee(employee: Employee) {
        employeeDao.deleteEmployee(employee)
    }
}