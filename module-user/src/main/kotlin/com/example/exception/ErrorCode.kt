package com.example.exception

enum class ErrorCode(val code: String, val reason: String) {
    ENTITY_NOT_FOUND("0001", "존재하지 않는 엔티티"),
    EXISTS_ENTITY("0002", "이미 존재하는 엔티티"),
    PRODUCT_NOT_FOUND("1001", "존재하지 않는 상품"),
    PRODUCT_NOT_ENOUGH("1002", "상품의 재고 부족")
}
