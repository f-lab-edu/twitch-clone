package com.example.user.application.service

import com.example.user.application.port.`in`.ApproveStreamerUserUseCase
import com.example.user.application.port.`in`.RejectStreamerUserUseCase
import com.example.user.application.port.`in`.SuspendStreamerUserUseCase
import com.example.user.application.port.`in`.UpdateStreamerUserUseCase
import com.example.user.application.port.out.StreamerUserRepository
import com.example.user.domain.model.StreamerUser
import java.util.*

class UpdateStreamerUserService(private val streamerRepository: StreamerUserRepository)
    : ApproveStreamerUserUseCase, RejectStreamerUserUseCase, SuspendStreamerUserUseCase, UpdateStreamerUserUseCase {

    override fun approveStreamerUser(streamerUsers: List<StreamerUser>) {
        streamerUsers.map {
            it.register()
        }
        streamerRepository.saveAll(streamerUsers)
    }

    override fun rejectStreamerUser(streamerUsers: List<StreamerUser>) {
        streamerUsers.map {
            it.reject()
        }
        streamerRepository.saveAll(streamerUsers)
    }

    override fun suspendStreamer(id: UUID) {
        val streamerUser = streamerRepository.findById(id)
        streamerUser.suspend()
        streamerRepository.save(streamerUser)
    }

    override fun updateStreamerNickname(id: UUID, updateNickname: String) {
        val streamerUser = streamerRepository.findById(id)
        streamerUser.streamerNickname = updateNickname
        streamerRepository.save(streamerUser)
    }

}
