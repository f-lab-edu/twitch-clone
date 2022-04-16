package com.example.config.exception

import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class ControllerAdvice {

    /**
     * 외부 통신 실패
     */
    @ExceptionHandler(HttpClientErrorException::class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    protected fun handleHttpClientErrorException(exception: HttpClientErrorException): ErrorResponse {
        preHandle(exception)
        return ErrorResponse.of(exception.message)
    }

    /**
     * 지원하지 않는 HTTP method가 호출 되는 경우
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    protected fun handleHttpRequestMethodNotSupportedException(exception: HttpRequestMethodNotSupportedException): ErrorResponse {
        preHandle(exception)
        return ErrorResponse.of(exception.message)
    }

    /**
     * 필수 requestParam이 누락된 경우
     */
    @ExceptionHandler(MissingServletRequestParameterException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected fun handleMissingServletRequestParameterException(exception: MissingServletRequestParameterException): ErrorResponse {
        preHandle(exception)
        return ErrorResponse.of(exception)
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected fun handleException(exception: Exception): ErrorResponse {
        preHandle(exception)
        return ErrorResponse.of(exception.message)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected fun handleHttpMessageNotReadableException(exception: HttpMessageNotReadableException): ErrorResponse {
        preHandle(exception)
        return ErrorResponse.of(exception.message)
    }

    /**
     * ModelAttribute 에 binding error 발생시 BindException 발생한다.
     */
    @ExceptionHandler(BindException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected fun handleBindException(exception: BindException): ErrorResponse {
        preHandle(exception)
        return ErrorResponse.of(ErrorCode.BAD_REQUEST, exception.bindingResult)
    }

    /**
     * type이 일치하지 않아 binding 하지 못하는 경우
     * 주로 @RequestParam enum으로 binding 못했을 경우 발생
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected fun handleMethodArgumentTypeMismatchException(exception: MethodArgumentTypeMismatchException): ErrorResponse {
        preHandle(exception)
        return ErrorResponse.of(exception)
    }

    /**
     * javax.validation을 통과하지 못하면 경우
     * ex) @NotBlank, @NotEmpty, @NotNull
     */
    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected fun handleConstraintViolationException(exception: ConstraintViolationException): ErrorResponse {
        preHandle(exception)
        return ErrorResponse.of(exception)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected fun handleIllegalArgumentException(exception: IllegalArgumentException): ErrorResponse {
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
    protected fun handleMethodArgumentNotValidException(exception: MethodArgumentNotValidException): ErrorResponse {
        preHandle(exception)
        return ErrorResponse.of(ErrorCode.BAD_REQUEST, exception.bindingResult)
    }

    private fun preHandle(exception: Exception) {
        // slf4j 적용 필요
        // log.error("### message={}, cause={}", ex.message, ex.cause, ex)
        println("### message=" + exception.message + ", cause=" + exception.cause)
    }
}
