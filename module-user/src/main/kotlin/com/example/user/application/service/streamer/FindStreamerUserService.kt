package com.example.user.application.service.streamer

import com.example.user.application.port.`in`.streamer.FindPendingStreamerUserUseCase
import com.example.user.application.port.`in`.streamer.FindStreamerUserQuery
import com.example.user.application.port.`in`.streamer.FindStreamerUserUseCase
import com.example.user.application.port.out.StreamerUserRepository
import com.example.user.domain.model.StreamerUser
import com.example.user.domain.model.StreamerUserStatus

class FindStreamerUserService(private val streamerRepository: StreamerUserRepository) : FindPendingStreamerUserUseCase, FindStreamerUserUseCase {

    override fun findPendingStreamers(): List<StreamerUser> {
        return streamerRepository.findAllByStatus(StreamerUserStatus.PENDING)
    }

    override fun findStreamers(findStreamerUserQuery: FindStreamerUserQuery): List<StreamerUser> {
        return with(findStreamerUserQuery) {
            streamerRepository.findStreamers(streamerNickname, streamerUserStatus)
        }
    }
}
