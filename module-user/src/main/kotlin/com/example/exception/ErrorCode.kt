package com.example.exception

enum class ErrorCode(val code: String, val reason: String) {
    ENTITY_NOT_FOUND("0001", "존재하지 않는 엔티티"),
    EXISTS_ENTITY("0002", "이미 존재하는 엔티티")
}
