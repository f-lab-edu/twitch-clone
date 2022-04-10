package com.example.user.application.service.normal

import com.example.exception.CustomException
import com.example.exception.ErrorCode
import com.example.user.application.port.`in`.normal.CreateNormalUserCommand
import com.example.user.application.port.`in`.normal.CreateNormalUserUseCase
import com.example.user.application.port.out.NormalUserRepository
import com.example.user.domain.model.NormalUser

internal class CreateNormalUserService(private val normalUserRepository: NormalUserRepository) : CreateNormalUserUseCase {

    override fun createNormalUser(createNormalUserCommand: CreateNormalUserCommand): NormalUser {
        with(createNormalUserCommand) {
            if (normalUserRepository.search(email = email).isNotEmpty()) {
                throw CustomException(ErrorCode.EXISTS_ENTITY)
            }

            return normalUserRepository.save(
                NormalUser(email = email, password = password, nickname = nickname)
            )
        }
    }
}
