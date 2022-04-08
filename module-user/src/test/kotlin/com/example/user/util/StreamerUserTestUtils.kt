package com.example.user.util

import com.example.user.application.port.`in`.CreateStreamerUserCommand
import com.example.user.domain.model.NormalUser
import com.example.user.domain.model.StreamerUser
import com.github.javafaker.Faker
import java.util.*

fun randomStreamUser(
    id: UUID? = null,
    email: String? = null,
    password: String? = null,
    nickname: String? = null,
    streamerNickname: String? = null,
    subscriptionCost: Long? = null
): StreamerUser = with(Faker()) {
    StreamerUser(
        id = id ?: UUID.randomUUID(),
        email = email ?: internet().emailAddress(),
        password = password ?: lorem().characters(10),
        nickname = nickname ?: name().name(),
        streamerNickname = streamerNickname ?: name().name(),
        subscriptionCost = subscriptionCost ?: number().numberBetween(5000L, 10000L),
    )
}

fun randomCreateStreamerUserCommand(
    normalUser: NormalUser? = null,
    streamerNickname: String? = null,
    subscriptionCost: Long? = null
): CreateStreamerUserCommand = with(Faker()) {
    CreateStreamerUserCommand(
        normalUser = normalUser ?: randomUser(),
        streamerUserNickname = streamerNickname ?: name().name(),
        subscriptionCost = subscriptionCost ?: number().numberBetween(5000L, 10000L),
    )
}
