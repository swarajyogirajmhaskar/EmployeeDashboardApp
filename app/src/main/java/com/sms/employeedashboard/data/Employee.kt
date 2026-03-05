package com.sms.employeedashboard.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "employees_data")
data class Employee(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val email: String,
    val department: String,
    val designation: String
) : Serializable