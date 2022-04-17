package com.example.config.exception

import org.slf4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.client.HttpClientErrorException

@RestControllerAdvice
class BaseControllerAdvice(log: Logger) : AbstractControllerAdvice(log) {

    /**
     * 외부 통신 실패
     */
    @ExceptionHandler(HttpClientErrorException::class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    internal fun handleHttpClientErrorException(exception: HttpClientErrorException): ErrorResponse {
        preHandle(exception)
        return ErrorResponse.of(exception.message)
    }

    /**
     * 지원하지 않는 HTTP method가 호출 되는 경우
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    internal fun handleHttpRequestMethodNotSupportedException(exception: HttpRequestMethodNotSupportedException): ErrorResponse {
        preHandle(exception)
        return ErrorResponse.of(exception.message)
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    internal fun handleException(exception: Exception): ErrorResponse {
        preHandle(exception)
        return ErrorResponse.of(exception.message)
    }
}
