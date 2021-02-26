package com.example.demo.dto

import com.example.demo.model.TypeOfWorker

class EmployeeDTO(
        val id: Long,
        val employeeName: String,
        val workedHours: Int?,
        val salary: Int,
        val type: TypeOfWorker,
        val baseSalary: Int?,
        val coef: Double?,
        val sales: Int?,
        val hourlySalary: Int?,
        val percentage: Int?
)
{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EmployeeDTO

        if (id != other.id) return false
        if (employeeName != other.employeeName) return false
        if (workedHours != other.workedHours) return false
        if (salary != other.salary) return false
        if (type != other.type) return false
        if (baseSalary != other.baseSalary) return false
        if (coef != other.coef) return false
        if (sales != other.sales) return false
        if (hourlySalary != other.hourlySalary) return false
        if (percentage != other.percentage) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + employeeName.hashCode()
        result = 31 * result + (workedHours ?: 0)
        result = 31 * result + salary
        result = 31 * result + type.hashCode()
        result = 31 * result + (baseSalary ?: 0)
        result = 31 * result + (coef?.hashCode() ?: 0)
        result = 31 * result + (sales ?: 0)
        result = 31 * result + (hourlySalary ?: 0)
        result = 31 * result + (percentage ?: 0)
        return result
    }
}

class EmployeeUpdateOptions(
        val employeeName: String? = null,
        val workedHours: Int? = null,
        val type: TypeOfWorker? = null,
        val baseSalary: Int? = null,
        val salary: Int? = null,
        val coef: Double? = null,
        val sales: Int? = null,
        val hourlySalary: Int? = null,
        val percentage: Int? = null

)