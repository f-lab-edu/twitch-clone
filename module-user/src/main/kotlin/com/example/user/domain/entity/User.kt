package com.example.user.domain.entity

import java.util.*

/**
 * 회원
 */
class User(
    val id: UUID = UUID.randomUUID(),
    val email: String,
    val password: String,
    val nickname: String,
    val status: UserStatus = UserStatus.REGISTERED
)
