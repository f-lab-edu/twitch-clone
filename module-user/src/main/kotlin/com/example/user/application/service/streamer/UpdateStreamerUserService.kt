package com.example.user.application.service.streamer

import com.example.user.application.port.`in`.streamer.ApproveStreamerUserUseCase
import com.example.user.application.port.`in`.streamer.RejectStreamerUserUseCase
import com.example.user.application.port.`in`.streamer.SuspendStreamerUserUseCase
import com.example.user.application.port.`in`.streamer.UpdateStreamerUserSubscriptionCostUseCase
import com.example.user.application.port.`in`.streamer.UpdateStreamerUserUseCase
import com.example.user.application.port.out.StreamerUserRepository
import com.example.user.domain.model.StreamerUser
import java.util.*

class UpdateStreamerUserService(private val streamerRepository: StreamerUserRepository) : ApproveStreamerUserUseCase, RejectStreamerUserUseCase,
    SuspendStreamerUserUseCase, UpdateStreamerUserUseCase, UpdateStreamerUserSubscriptionCostUseCase {

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
        val streamerUser = streamerRepository.findById(id).apply {
            suspend()
        }
        streamerRepository.save(streamerUser)
    }

    override fun updateStreamerNickname(id: UUID, updateNickname: String) {
        val streamerUser = streamerRepository.findById(id).apply {
            streamerNickname = updateNickname
        }
        streamerRepository.save(streamerUser)
    }

    override fun updateSubscriptionCost(id: UUID, updateSubscriptionCost: Int) {
        val streamerUser = streamerRepository.findById(id).apply {
            subscriptionCost = updateSubscriptionCost
        }
        streamerRepository.save(streamerUser)
    }
}
