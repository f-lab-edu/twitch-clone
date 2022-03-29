package com.example.user.application.service

import com.example.user.application.port.`in`.FindStreamerUserUseCase
import com.example.user.application.port.out.StreamerUserRepository
import com.example.user.domain.model.StreamerUser
import com.example.user.domain.model.StreamerUserStatus

class FindStreamerUserService(private val streamerRepository: StreamerUserRepository) : FindStreamerUserUseCase {

    override fun findPendingStreamers() : List<StreamerUser> {
        return streamerRepository.findAllByStatus(StreamerUserStatus.PENDING)
    }
}
