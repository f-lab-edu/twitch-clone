package com.example.user.util

import com.example.user.application.port.`in`.CreateStreamerUserCommand
import com.example.user.domain.model.StreamerUser
import com.example.user.domain.model.NormalUser
import com.github.javafaker.Faker

fun createStreamUser(
    user: NormalUser = randomUser(),
    streamerNickname: String? = null,
): StreamerUser = with(Faker()) {
    StreamerUser(
        user = user,
        streamerNickname = streamerNickname ?: name().name()
    )
}

fun randomCreateStreamerUserCommand(
    user: NormalUser = randomUser(),
    streamerNickname: String? = null,
): CreateStreamerUserCommand = with(Faker()) {
    CreateStreamerUserCommand(
        user = user,
        streamerUserNickname = streamerNickname ?: name().name()
    )
}
