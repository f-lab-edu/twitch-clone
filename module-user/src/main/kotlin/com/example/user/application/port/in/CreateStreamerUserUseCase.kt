package com.example.user.application.port.`in`

import com.example.user.domain.model.StreamerUser
import com.example.user.domain.model.User
import java.util.*

interface CreateStreamerUserUseCase {

    fun createStreamerUser(createStreamerUserCommand: CreateStreamerUserCommand): StreamerUser
}

data class CreateStreamerUserCommand(val user: User, val streamerUserNickname: String) {
    internal val id : UUID
        get() {
            return user.id
        }
}
