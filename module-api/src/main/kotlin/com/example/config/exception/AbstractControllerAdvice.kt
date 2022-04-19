package com.example.config.exception

import org.slf4j.Logger
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class AbstractControllerAdvice(private val log: Logger) {

    internal fun preHandle(exception: Exception) {
        log.error("### message={}, cause={}", exception.message, exception.cause, exception)
    }
}
