package com.example.demo.exception

class EmployeeAlreadyExistsException(name: String) : RuntimeException("Employee $name is already exists")
class EmployeeNameNotFoundByNameException(name: String) : RuntimeException("Employee $name is not found exists")
class EmployeeNameNotFoundByIdException(id: Long) : NullPointerException("No such employee by $id")
class ThrowMissingFieldException(field: String) : RuntimeException("Missing field $field")

