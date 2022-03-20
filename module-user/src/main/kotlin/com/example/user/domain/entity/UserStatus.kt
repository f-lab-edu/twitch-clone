package com.example.user.domain.entity

enum class UserStatus(val description: String) {
    REGISTERED("등록"),
    SUSPENSE("정지"),
    UNREGISTERED("탈퇴")
}
