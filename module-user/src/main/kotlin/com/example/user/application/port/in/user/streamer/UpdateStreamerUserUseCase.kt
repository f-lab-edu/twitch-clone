package com.example.user.application.port.`in`.user.streamer

import java.util.*

interface UpdateStreamerUserUseCase {

    fun updateStreamerNickname(id: UUID, updateNickname: String)
}
