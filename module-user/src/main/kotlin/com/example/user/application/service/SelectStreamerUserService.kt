package com.example.user.application.service

import com.example.user.application.port.`in`.SelectStreamerUserUseCase
import com.example.user.application.port.out.StreamerUserRepository
import com.example.user.domain.model.StreamerUser
import com.example.user.domain.model.StreamerUserStatus

class SelectStreamerUserService(private val streamerRepository: StreamerUserRepository) : SelectStreamerUserUseCase {

    override fun selectPendingStreamers() : List<StreamerUser> {
        return streamerRepository.findAllByStatus(StreamerUserStatus.PENDING)
            .ifEmpty { emptyList() }
    }
}
