package com.infinum.academyproject.config

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler
    fun handleIllegalArgumentException(ex: IllegalArgumentException) =
        ResponseEntity(ex.message ?: "Illegal arguments passed", HttpStatus.BAD_REQUEST)
}