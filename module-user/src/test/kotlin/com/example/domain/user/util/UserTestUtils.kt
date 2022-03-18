package com.example.domain.user.util

import com.example.domain.user.entity.User
import com.example.domain.user.entity.UserStatus
import com.github.javafaker.Faker
import java.util.*

fun randomUser(
    id: UUID? = null,
    email: String? = null,
    password: String? = null,
    nickName: String? = null,
    status: UserStatus? = null
): User = with(Faker()) {
    User(
        id = id ?: UUID.randomUUID(),
        email = email ?: internet().emailAddress(),
        password = password ?: lorem().characters(10),
        nickName = nickName ?: name().name(),
        status = status ?: UserStatus.REGISTERED
    )
}
