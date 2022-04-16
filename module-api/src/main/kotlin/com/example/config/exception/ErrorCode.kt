package com.example.config.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(val code: String, val status: HttpStatus, val reason: String) {
    BAD_REQUEST("0400", HttpStatus.BAD_REQUEST, "잘못된 입력 값"),
    UNAUTHORIZED("0401", HttpStatus.UNAUTHORIZED, "인증 실패"),
    FORBIDDEN("0403", HttpStatus.FORBIDDEN, "권한 없음"),
    NOT_FOUND("0404", HttpStatus.NOT_FOUND, "찾을 수 없음"),
    METHOD_NOT_ALLOWED("0405", HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 메소드"),
    UNKNOWN("0500", HttpStatus.INTERNAL_SERVER_ERROR, "알수 없는 서버 에러"),
}
