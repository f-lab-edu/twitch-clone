package com.example.user.application.port.`in`.streamer

import java.util.*

interface UpdateStreamerUserUseCase {

    fun updateStreamerNickname(id: UUID, updateNickname: String)
}
