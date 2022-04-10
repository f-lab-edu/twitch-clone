package com.example.user.util

import com.example.user.application.port.`in`.user.admin.CreateAdminUserCommand
import com.example.user.application.port.`in`.user.normal.CreateNormalUserCommand
import com.example.user.application.port.`in`.user.streamer.CreateStreamerUserCommand
import com.example.user.domain.model.AdminUser
import com.example.user.domain.model.NormalUser
import com.example.user.domain.model.StreamerUser
import com.github.javafaker.Faker
import java.util.*

object TestUserGenerator {

    fun streamUser(
        id: UUID? = null,
        email: String? = null,
        password: String? = null,
        nickname: String? = null,
        streamerNickname: String? = null,
        subscriptionCost: Int? = null
    ): StreamerUser = with(Faker()) {
        StreamerUser(
            id = id ?: UUID.randomUUID(),
            email = email ?: internet().emailAddress(),
            password = password ?: lorem().characters(10),
            nickname = nickname ?: name().name(),
            streamerNickname = streamerNickname ?: name().name(),
            subscriptionCost = subscriptionCost ?: number().numberBetween(5000, 10000),
        )
    }

    fun createStreamerUserCommand(
        normalUser: NormalUser? = null,
        streamerNickname: String? = null,
        subscriptionCost: Int? = null
    ): CreateStreamerUserCommand = with(Faker()) {
        CreateStreamerUserCommand(
            normalUser = normalUser ?: normalUser(),
            streamerUserNickname = streamerNickname ?: name().name(),
            subscriptionCost = subscriptionCost ?: number().numberBetween(5000, 10000),
        )
    }

    fun normalUser(
        id: UUID? = null,
        email: String? = null,
        password: String? = null,
        nickname: String? = null,
    ): NormalUser = with(Faker()) {
        NormalUser(
            id = id ?: UUID.randomUUID(),
            email = email ?: internet().emailAddress(),
            password = password ?: lorem().characters(10),
            nickname = nickname ?: name().name(),
        )
    }

    fun createNormalUserCommand(
        email: String? = null,
        password: String? = null,
        nickname: String? = null,
    ): CreateNormalUserCommand = with(Faker()) {
        CreateNormalUserCommand(
            email = email ?: internet().emailAddress(),
            password = password ?: lorem().characters(10),
            nickname = nickname ?: name().name(),
        )
    }

    fun adminUser(
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

    fun createAdminUserCommand(
        normalUser: NormalUser? = null,
        adminUserNickname: String? = null,
    ): CreateAdminUserCommand = with(Faker()) {
        CreateAdminUserCommand(
            normalUser = normalUser ?: normalUser(),
            adminUserNickname = adminUserNickname ?: name().name()
        )
    }
}
