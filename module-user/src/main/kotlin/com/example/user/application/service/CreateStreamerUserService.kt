package com.example.user.application.service

import com.example.exception.CustomException
import com.example.exception.ErrorCode
import com.example.user.application.port.`in`.CreateStreamerUserCommand
import com.example.user.application.port.`in`.CreateStreamerUserUseCase
import com.example.user.application.port.out.StreamerUserRepository
import com.example.user.domain.model.StreamerUser

internal class CreateStreamerUserService(private val streamerRepository: StreamerUserRepository) : CreateStreamerUserUseCase {

    override fun createStreamerUser(createStreamerUserCommand: CreateStreamerUserCommand): StreamerUser {
        if (streamerRepository.existsById(createStreamerUserCommand.id)) {
            throw CustomException(ErrorCode.EXISTS_ENTITY)
        }

        return with(createStreamerUserCommand) {
            streamerRepository.save(
                StreamerUser(
                    id = normalUser.id,
                    email = normalUser.email,
                    password = normalUser.password,
                    nickname = normalUser.nickname,
                    streamerNickname = streamerUserNickname,
                    subscriptionCost = subscriptionCost
                )
            )
        }
    }
}
