package com.example.demo.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class RestResponseEntityExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler
    fun handle(e: Exception):ResponseEntity<String> = when(e){
        is EmployeeAlreadyExistsException -> ResponseEntity(e.localizedMessage, HttpStatus.BAD_REQUEST)
        is EmployeeNameNotFoundByNameException -> ResponseEntity(e.localizedMessage, HttpStatus.NOT_FOUND)
        is EmployeeNameNotFoundByIdException -> ResponseEntity(e.localizedMessage, HttpStatus.NOT_FOUND)
        is ThrowMissingFieldException -> ResponseEntity(e.localizedMessage, HttpStatus.BAD_REQUEST)
        else -> ResponseEntity(e.localizedMessage, HttpStatus.UNAUTHORIZED)
    }
}