package com.example.user.application.port.`in`

import com.example.user.domain.model.StreamerUser
import java.util.*

interface CreateStreamerUserUseCase {

    fun createStreamerUser(createStreamerUserCommand: CreateStreamerUserCommand): StreamerUser
}

data class CreateStreamerUserCommand(
    val id: UUID,
    val email: String,
    val password: String,
    val nickname: String,
    val streamerUserNickname: String
)
