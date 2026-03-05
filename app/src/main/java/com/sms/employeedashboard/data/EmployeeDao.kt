package com.sms.employeedashboard.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEmployee(employee: Employee)

    @Query("SELECT * FROM employees_data ORDER BY id ASC")
    fun readAllData(): LiveData<List<Employee>>

    @Query("SELECT COUNT(*) FROM employees_data")
    fun getEmployeeCount(): LiveData<Int>

    @Update
    suspend fun updateEmployee(employee: Employee)

    @Delete
    suspend fun deleteEmployee(employee: Employee)
}