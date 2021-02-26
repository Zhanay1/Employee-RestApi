package com.example.demo.repository

import com.example.demo.model.EmployeeModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface  EmployeeRepository:JpaRepository<EmployeeModel, Long> {

    fun findByEmployeeName(name: String): EmployeeModel?


}