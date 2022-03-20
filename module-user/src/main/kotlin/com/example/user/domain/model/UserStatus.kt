package com.example.user.domain.model

enum class UserStatus(val description: String) {
    REGISTERED("등록"),
    SUSPENSE("정지"),
    UNREGISTERED("탈퇴")
}
