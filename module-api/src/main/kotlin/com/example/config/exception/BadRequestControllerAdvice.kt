package com.example.config.exception

import org.slf4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class BadRequestControllerAdvice(log: Logger) : AbstractControllerAdvice(log) {

    /**
     * type이 일치하지 않아 binding 하지 못하는 경우
     * 주로 @RequestParam enum으로 binding 못했을 경우 발생
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    internal fun handleMethodArgumentTypeMismatchException(exception: MethodArgumentTypeMismatchException): ErrorResponse {
        preHandle(exception)
        return ErrorResponse.of(exception)
    }

    /**
     * javax.validation을 통과하지 못하면 경우
     * ex) @NotBlank, @NotEmpty, @NotNull
     */
    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    internal fun handleConstraintViolationException(exception: ConstraintViolationException): ErrorResponse {
        preHandle(exception)
        return ErrorResponse.of(exception)
    }

    /**
     * 필수 requestParam이 누락된 경우
     */
    @ExceptionHandler(MissingServletRequestParameterException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    internal fun handleMissingServletRequestParameterException(exception: MissingServletRequestParameterException): ErrorResponse {
        preHandle(exception)
        return ErrorResponse.of(exception)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    internal fun handleHttpMessageNotReadableException(exception: HttpMessageNotReadableException): ErrorResponse {
        preHandle(exception)
        return ErrorResponse.of(exception.message)
    }

    /**
     * ModelAttribute 에 binding error 발생시 BindException 발생한다.
     */
    @ExceptionHandler(BindException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    internal fun handleBindException(exception: BindException): ErrorResponse {
        preHandle(exception)
        return ErrorResponse.of(ErrorCode.BAD_REQUEST, exception.bindingResult)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    internal fun handleIllegalArgumentException(exception: IllegalArgumentException): ErrorResponse {
        preHandle(exception)
        return ErrorResponse.of(ErrorCode.BAD_REQUEST)
    }

    /**
     * Valid or Validated 으로 binding error 시 발생
     * HttpMessageConverter 에서 등록한 HttpMessageConverter binding 이 실패하는 경우
     * ex) @RequestBody, @RequestPart
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    internal fun handleMethodArgumentNotValidException(exception: MethodArgumentNotValidException): ErrorResponse {
        preHandle(exception)
        return ErrorResponse.of(ErrorCode.BAD_REQUEST, exception.bindingResult)
    }
}
