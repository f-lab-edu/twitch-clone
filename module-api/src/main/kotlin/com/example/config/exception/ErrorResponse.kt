package com.example.config.exception

import org.springframework.validation.BindingResult
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import java.time.LocalDateTime
import java.util.stream.Collectors
import javax.validation.ConstraintViolationException
import javax.validation.Path

data class ErrorResponse(
    val code: String = "",
    val codeName: String = "",
    val message: String = "",
    val time: LocalDateTime = LocalDateTime.now(),
    val errors: List<FieldError> = ArrayList()
) {
    companion object {
        private const val DEFAULT_MESSAGE = "기본 에러 메시지가 존재하지 않습니다."

        fun of(errorCode: ErrorCode, bindingResult: BindingResult): ErrorResponse {
            return ErrorResponse(
                code = errorCode.code,
                codeName = errorCode.name,
                message = errorCode.reason,
                errors = FieldError.of(bindingResult)
            )
        }

        fun of(errorCode: ErrorCode): ErrorResponse {
            return ErrorResponse(
                code = errorCode.code,
                codeName = errorCode.name,
                message = errorCode.reason,
            )
        }

        fun of(message: String?): ErrorResponse {
            return ErrorResponse(message = message ?: DEFAULT_MESSAGE)
        }

        fun of(exception: MethodArgumentTypeMismatchException): ErrorResponse {
            val fieldError = with(exception) {
                FieldError.of(
                    field = name,
                    value = value?.toString() ?: "",
                    reason = errorCode
                )
            }
            return of(ErrorCode.BAD_REQUEST, listOf(fieldError))
        }

        fun of(exception: MissingServletRequestParameterException): ErrorResponse {
            val fieldError = FieldError.of(
                field = exception.parameterName,
                value = "",
                reason = "Not exist"
            )
            return of(ErrorCode.BAD_REQUEST, listOf(fieldError))
        }

        fun of(exception: ConstraintViolationException): ErrorResponse {
            val fieldErrors = exception.constraintViolations.stream()
                .map {
                    FieldError.of(
                        field = extractPropertyName(it.propertyPath),
                        value = "",
                        reason = it.message
                    )
                }.collect(Collectors.toList())
            return of(ErrorCode.BAD_REQUEST, fieldErrors)
        }

        private fun of(errorCode: ErrorCode, errors: List<FieldError>): ErrorResponse {
            return ErrorResponse(
                code = errorCode.code,
                codeName = errorCode.name,
                message = errorCode.reason,
                errors = errors
            )
        }

        /**
         * 속성 전체 경로에서 속성 이름만 가져옵니다.
         */
        private fun extractPropertyName(propertyPath: Path): String {
            val pathString = propertyPath.toString()
            return pathString.substring(pathString.lastIndexOf('.') + 1)
        }
    }

    /**
     * BAD_REQUEST를 상세히 알려주기 위한 필드 에러
     */
    data class FieldError(
        val field: String,
        val value: String,
        val reason: String,
    ) {

        companion object {
            fun of(field: String, value: String, reason: String): FieldError {
                return FieldError(field, value, reason)
            }

            fun of(bindingResult: BindingResult): List<FieldError> {
                return with(bindingResult) {
                    fieldErrors.stream()
                        .map { of(it) }
                        .collect(Collectors.toList())
                }
            }

            private fun of(fieldError: org.springframework.validation.FieldError): FieldError {
                return with(fieldError) {
                    FieldError(
                        field = field,
                        value = rejectedValue?.toString() ?: "",
                        reason = defaultMessage ?: ""
                    )
                }
            }
        }
    }
}
