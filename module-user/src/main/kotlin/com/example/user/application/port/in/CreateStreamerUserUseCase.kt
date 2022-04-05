package com.example.user.application.port.`in`

import com.example.user.domain.model.StreamerUser
import com.example.user.domain.model.NormalUser
import java.util.*

interface CreateStreamerUserUseCase {

    fun createStreamerUser(createStreamerUserCommand: CreateStreamerUserCommand): StreamerUser
}

data class CreateStreamerUserCommand(val user: NormalUser, val streamerUserNickname: String) {
    internal val id : UUID
        get() {
            return user.id
        }
}
