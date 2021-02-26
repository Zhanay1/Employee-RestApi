package com.example.demo.controller

import com.example.demo.dto.EmployeeDTO
import com.example.demo.model.TypeOfWorker
import com.example.demo.service.EmployeeService
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal


@RestController
@RequestMapping("/employee")
class EmployeeController(
        private val employeeService: EmployeeService,
) {
    @GetMapping("/employees")
    fun getEmployees() = employeeService.findAll()

    @PostMapping("/add/name")
    fun createEmployee(
            @RequestParam("name", required = true) name: String,
            @RequestParam("type", required = true) type: TypeOfWorker,
            @RequestParam("workedHours", required = false) workedHours: Int?,
            @RequestParam("baseSalary", required = false) baseSalary: Int?,
            @RequestParam("coef", required = false) coef: Double?,
            @RequestParam("sales", required = false) sales: Int?,
            @RequestParam("hourlySalary", required = false) hourlySalary: Int?,
            @RequestParam("percentage", required = false) percentage: Int?
    ) = employeeService.addEmployee(
            employeeDTO = EmployeeDTO(
                    id = 0,
                    employeeName = name,
                    workedHours = workedHours,
                    salary = 0,
                    type = type,
                    baseSalary = baseSalary,
                    coef = coef,
                    sales = sales,
                    hourlySalary = hourlySalary,
                    percentage = percentage
            )
    )


    @GetMapping("/see-total-salary")
    fun seeTotalSalary(
        @RequestParam("name", required = true) name: String
    ) = employeeService.getEmployeeByName(
            name = name
    )

    @GetMapping("change-base-salary")
    fun changeBaseSalary(
            @RequestParam("name", required = true) name: String,
            @RequestParam("newBaseSalary", required = true) newBaseSalary: Int
    ) = employeeService.changeBaseSalary(
            name = name,
            newBaseSalary = newBaseSalary
    )


}