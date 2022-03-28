package com.example.user.application.service

import com.example.user.application.port.`in`.UpdateStreamerUserUseCase
import com.example.user.application.port.out.StreamerUserRepository
import com.example.user.domain.model.StreamerUser
import com.example.user.domain.model.StreamerUserStatus
import java.util.*

class UpdateStreamerUserService(private val streamerRepository: StreamerUserRepository) : UpdateStreamerUserUseCase {

    override fun approveStreamerUser(streamerUsers: List<StreamerUser>) {
        val updateStreamerUsers = streamerUsers.map {
            StreamerUser(
                user = it.user,
                streamerNickname = it.streamerNickname,
                status = StreamerUserStatus.REGISTERED
            )
        }
        streamerRepository.saveAll(updateStreamerUsers)
    }

    override fun rejectStreamerUser(streamerUsers: List<StreamerUser>) {
        val updateStreamerUsers = streamerUsers.map {
            StreamerUser(
                user = it.user,
                streamerNickname = it.streamerNickname,
                status = StreamerUserStatus.REJECTED
            )
        }
        streamerRepository.saveAll(updateStreamerUsers)
    }

    override fun suspendStreamer(id: UUID) {
        val streamerUser = streamerRepository.findById(id)
        streamerRepository.save(StreamerUser(
            user = streamerUser.user,
            streamerNickname = streamerUser.streamerNickname,
            status = StreamerUserStatus.SUSPENSE
        ))
    }

}
