package com.example.demo.service

import com.example.demo.dto.EmployeeDTO
import com.example.demo.dto.EmployeeUpdateOptions
import com.example.demo.exception.*
import com.example.demo.model.EmployeeModel
import com.example.demo.model.TypeOfWorker
import com.example.demo.model.toDTO
import com.example.demo.repository.EmployeeRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class EmployeeService(
        private val employeeRepository: EmployeeRepository
) {
    fun findAll() = employeeRepository.findAll().map{
        it.toDTO()
    }

    @Transactional
    fun addEmployee(employeeDTO: EmployeeDTO): EmployeeDTO{

        val employee = employeeRepository.findByEmployeeName(name = employeeDTO.employeeName)
        if(employee != null) throw EmployeeAlreadyExistsException(employeeDTO.employeeName)

        return employeeRepository.save(
                EmployeeModel(
                        id = 0,
                        employeeName = employeeDTO.employeeName,
                        workedHours = employeeDTO.workedHours,
                        type = employeeDTO.type,
                        salary = getTotalSalary(employeeDTO),
                        sales = employeeDTO.sales,
                        baseSalary = employeeDTO.baseSalary,
                        hourlySalary = employeeDTO.hourlySalary,
                        percentage = employeeDTO.percentage,
                        coef = employeeDTO.coef
                )
        ).toDTO()
    }

    @Transactional
    fun getEmployeeByName(name: String): EmployeeDTO{
        return (employeeRepository.findByEmployeeName(name) ?: throw EmployeeNameNotFoundByNameException(name)).toDTO()
    }

    @Transactional
    fun changeBaseSalary(name: String, newBaseSalary: Int): EmployeeDTO{
        val employee = getEmployeeByName(name = name)
         update(
            id = employee.id,
            updateOptions = EmployeeUpdateOptions(
                    baseSalary = newBaseSalary,
                    salary = getTotalSalary(employeeDTO = employee)
            )
        )
        return update(
                id = employee.id,
                updateOptions = EmployeeUpdateOptions(
                        salary = getTotalSalary(newBaseSalary, employeeDTO = employee)
                )
        )
    }

    @Transactional
    fun getTotalSalary(employeeDTO: EmployeeDTO): Int{
        var totalSalary = 0
        with(employeeDTO) {
            when(type) {
                TypeOfWorker.BASE -> totalSalary = baseSalary ?: throw ThrowMissingFieldException("baseSalary")
                TypeOfWorker.HOURLY -> {
                    (workedHours ?: throw ThrowMissingFieldException("workedHours"))
                    (hourlySalary ?: throw ThrowMissingFieldException("hourlySalary"))
                    (coef ?: throw ThrowMissingFieldException("coefficient"))

                    totalSalary = if(workedHours > 40) ((hourlySalary * 40) + (coef * hourlySalary * (workedHours - 40))).toInt()
                    else workedHours * hourlySalary
                }
                TypeOfWorker.COMMISSION -> {
                    (percentage ?: throw ThrowMissingFieldException("percentage"))
                    (sales ?: throw ThrowMissingFieldException("sales"))
                    totalSalary = (percentage * sales) / 100
                }
                TypeOfWorker.BASE_WITH_COMMISSION -> {
                    (percentage ?: throw ThrowMissingFieldException("percentage"))
                    (sales ?: throw ThrowMissingFieldException("sales"))
                    (baseSalary ?: throw ThrowMissingFieldException("baseSalary"))
                    totalSalary = baseSalary + ((sales * percentage) / 100)
                }
            }
        }
        return totalSalary
    }

    @Transactional
    fun getTotalSalary(baseSalary: Int?, employeeDTO: EmployeeDTO): Int{
        var totalSalary = 0
        with(employeeDTO) {
            when(type) {
                TypeOfWorker.BASE -> totalSalary = baseSalary ?: throw ThrowMissingFieldException("baseSalary")
                TypeOfWorker.HOURLY -> {
                    (workedHours ?: throw ThrowMissingFieldException("workedHours"))
                    (hourlySalary ?: throw ThrowMissingFieldException("hourlySalary"))
                    (coef ?: throw ThrowMissingFieldException("coefficient"))

                    totalSalary = if(workedHours > 40) ((hourlySalary * 40) + (coef * hourlySalary * (workedHours - 40))).toInt()
                    else workedHours * hourlySalary
                }
                TypeOfWorker.COMMISSION -> {
                    (percentage ?: throw ThrowMissingFieldException("percentage"))
                    (sales ?: throw ThrowMissingFieldException("sales"))
                    totalSalary = (percentage * sales) / 100
                }
                TypeOfWorker.BASE_WITH_COMMISSION -> {
                    (percentage ?: throw ThrowMissingFieldException("percentage"))
                    (sales ?: throw ThrowMissingFieldException("sales"))
                    (baseSalary ?: throw ThrowMissingFieldException("baseSalary"))
                    totalSalary = baseSalary + ((sales * percentage) / 100)
                }
            }
        }
        return totalSalary
    }


    fun update(id: Long, updateOptions: EmployeeUpdateOptions): EmployeeDTO{
        val employee = employeeRepository.findByIdOrNull(id) ?: throw  EmployeeNameNotFoundByIdException(id)

        if(updateOptions.employeeName != null) employee.employeeName = updateOptions.employeeName
        if(updateOptions.workedHours != null) employee.workedHours = updateOptions.workedHours
        if(updateOptions.type != null) employee.type = updateOptions.type
        if(updateOptions.baseSalary != null) employee.baseSalary = updateOptions.baseSalary
        if(updateOptions.salary != null) employee.salary = updateOptions.salary
        if(updateOptions.coef != null) employee.coef = updateOptions.coef
        if(updateOptions.sales != null) employee.sales = updateOptions.sales
        if(updateOptions.hourlySalary != null) employee.hourlySalary = updateOptions.hourlySalary
        if(updateOptions.percentage != null) employee.percentage = updateOptions.percentage

        return employee.toDTO()
    }


}