package com.example.user.util

import com.example.user.application.port.`in`.CreateAdminUserCommand
import com.example.user.domain.model.AdminUser
import com.example.user.domain.model.NormalUser
import com.github.javafaker.Faker
import java.util.*

fun randomAdminUser(
    id: UUID? = null,
    email: String? = null,
    password: String? = null,
    nickname: String? = null,
    adminNickname: String? = null,
): AdminUser = with(Faker()) {
    AdminUser(
        id = id ?: UUID.randomUUID(),
        email = email ?: internet().emailAddress(),
        password = password ?: lorem().characters(10),
        nickname = nickname ?: name().name(),
        adminNickname = adminNickname ?: name().name()
    )
}

fun randomCreateAdminUserCommand(
    normalUser: NormalUser? = null,
    adminUserNickname: String? = null,
): CreateAdminUserCommand = with(Faker()) {
    CreateAdminUserCommand(
        normalUser = normalUser?: randomUser(),
        adminUserNickname = adminUserNickname ?: name().name()
    )
}
