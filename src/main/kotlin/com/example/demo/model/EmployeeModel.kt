package com.example.demo.model

import com.example.demo.dto.EmployeeDTO
import javax.persistence.*


@Entity
@Table(name = "t_employee")
class EmployeeModel(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        var id: Long,

        @Column(name = "employeeName")
        var employeeName: String,

        @Column(name = "hours")
        var workedHours: Int?,

        @Column(name = "salary")
        var salary: Int,

        @Column
        @Enumerated(EnumType.STRING)
        var type: TypeOfWorker,

        @Column(name = "baseSalary")
        var baseSalary: Int?,

        @Column(name = "coefficient")
        var coef: Double?,

        @Column(name = "sales")
        var sales: Int?,

        @Column(name = "hourlySalary")
        var hourlySalary: Int?,

        @Column(name= "percentage")
        var percentage: Int?
)

fun EmployeeModel.toDTO() = EmployeeDTO(
        id = id,
        employeeName = employeeName,
        workedHours = workedHours,
        salary = salary,
        type = type,
        baseSalary = baseSalary,
        sales = sales,
        coef = coef,
        hourlySalary = hourlySalary,
        percentage = percentage,
)

enum class TypeOfWorker {
        BASE,
        HOURLY,
        COMMISSION,
        BASE_WITH_COMMISSION
}
