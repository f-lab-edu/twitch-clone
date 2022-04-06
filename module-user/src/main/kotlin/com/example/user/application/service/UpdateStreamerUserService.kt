package com.example.user.application.service

import com.example.user.application.port.`in`.UpdateStreamerUserUseCase
import com.example.user.application.port.out.StreamerUserRepository
import com.example.user.domain.model.StreamerUser
import java.util.*

class UpdateStreamerUserService(private val streamerRepository: StreamerUserRepository) : UpdateStreamerUserUseCase {

    override fun approveStreamerUser(streamerUsers: List<StreamerUser>) {
        streamerUsers.map {
            it.registeredStreamer()
        }
        streamerRepository.saveAll(streamerUsers)
    }

    override fun rejectStreamerUser(streamerUsers: List<StreamerUser>) {
        streamerUsers.map {
            it.rejectStreamerUser()
        }
        streamerRepository.saveAll(streamerUsers)
    }

    override fun suspendStreamer(id: UUID) {
        val streamerUser = streamerRepository.findById(id)
        streamerUser.suspendStreamer()
        streamerRepository.save(streamerUser)
    }

    override fun updateStreamerNickname(id: UUID, updateNickname: String) {
        val streamerUser = streamerRepository.findById(id)
        streamerUser.streamerNickname = updateNickname
        streamerRepository.save(streamerUser)
    }

}
