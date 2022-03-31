package com.example.user.util

import com.example.user.domain.model.StreamerUser
import com.example.user.domain.model.User
import com.example.user.domain.model.UserStatus
import com.github.javafaker.Faker
import java.util.*

fun createStreamUser(
    user: User = randomUser(),
    streamerNickname: String? = null,
): StreamerUser = with(Faker()) {
    StreamerUser(
        user = user,
        streamerNickname = streamerNickname ?: name().name()
    )
}
