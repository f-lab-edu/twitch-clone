package com.example.domain.user.entity

import java.util.*

/**
 * 회원
 */
class User(
    val id: UUID = UUID.randomUUID(),
    val email: String,
    val password: String,
    val nickName: String,
    val status: UserStatus = UserStatus.REGISTERED
)
