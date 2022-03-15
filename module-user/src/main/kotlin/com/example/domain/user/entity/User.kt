package com.example.domain.user.entity

/**
 * 회원
 */
class User(
    val email: String,
    val nickName: String,
    val status: UserStatus = UserStatus.REGISTERED
)
