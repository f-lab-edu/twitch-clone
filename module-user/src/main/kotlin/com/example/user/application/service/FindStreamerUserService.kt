package com.example.user.application.service

import com.example.user.application.port.`in`.user.streamer.FindPendingStreamerUserUseCase
import com.example.user.application.port.`in`.user.streamer.FindStreamerUserUseCase
import com.example.user.application.port.out.SearchStreamerQuery
import com.example.user.application.port.out.StreamerUserRepository
import com.example.user.domain.model.StreamerUser
import com.example.user.domain.model.StreamerUserStatus

class FindStreamerUserService(private val streamerRepository: StreamerUserRepository) : FindStreamerUserUseCase, FindPendingStreamerUserUseCase {

    override fun findStreamers(searchStreamerQuery: SearchStreamerQuery): List<StreamerUser> {
        return streamerRepository.findStreamers(searchStreamerQuery)
    }

    override fun findPendingStreamers(): List<StreamerUser> {
        return streamerRepository.findAllByStatus(StreamerUserStatus.PENDING)
    }
}
