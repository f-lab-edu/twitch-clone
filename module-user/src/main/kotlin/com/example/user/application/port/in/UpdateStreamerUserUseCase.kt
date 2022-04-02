package com.example.user.application.port.`in`

import com.example.user.domain.model.StreamerUser
import java.util.*

interface UpdateStreamerUserUseCase {

    fun approveStreamerUser(streamerUsers: List<StreamerUser>)

    fun rejectStreamerUser(streamerUsers: List<StreamerUser>)

    fun suspendStreamer(id: UUID)

    fun updateStreamerNickname(id: UUID, updateNickname: String)
}
