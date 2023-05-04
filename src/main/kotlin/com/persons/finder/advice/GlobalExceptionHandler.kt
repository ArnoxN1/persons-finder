package com.persons.finder.advice

import com.persons.finder.exceptions.CustomizeException
import com.persons.finder.presentation.R
import com.persons.finder.presentation.R.Companion.fail
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import java.lang.RuntimeException
import java.util.stream.Collectors
import javax.validation.ConstraintViolation
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class GlobalExceptionHandler() {

    private val logger: Logger? = LoggerFactory.getLogger(this::class.java)

    /**
     * solve exceptions when API's parameter's not in correct format
     */
    @ExceptionHandler(
        RuntimeException::class,
        CustomizeException::class,
        MethodArgumentNotValidException::class,
        HttpMessageNotReadableException::class,
        MethodArgumentTypeMismatchException::class
    )
    fun handle5xxServerError(ex: Exception): R<String> {
        logger!!.error("----------API Error----------",ex);

        var msg: String? = null;
        if (ex is CustomizeException) {
            msg = ex.message;
        } else if (ex is MethodArgumentNotValidException) {
            msg = ex.message!!.substring(ex.message!!.lastIndexOf('[')+1).replace("]","");
        } else if (ex is HttpMessageNotReadableException || ex is MethodArgumentTypeMismatchException) {
            msg = "please use the correct format for the parameters"
        } else {
            msg = "server error"
        }

        return fail(msg!!);
    }

    /**
     * combine with validation annotations used in PersonController
     */
    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleConstraintViolationException(cve: ConstraintViolationException): R<String> {
        logger!!.error("----------API Error----------",cve);

        return fail(
            cve.constraintViolations.stream().map { e -> e.message }.collect(Collectors.joining(","))
        )
    }
}