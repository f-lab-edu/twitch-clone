package com.example.user.application.service

import com.example.exception.CustomException
import com.example.exception.ErrorCode
import com.example.user.application.port.`in`.CreateNormalUserCommand
import com.example.user.application.port.`in`.CreateNormalUserUseCase
import com.example.user.application.port.out.NormalUserRepository
import com.example.user.application.port.out.SearchNormalUserQuery
import com.example.user.domain.model.NormalUser

internal class CreateNormalUserService(private val normalUserRepository: NormalUserRepository) : CreateNormalUserUseCase {

    override fun createNormalUser(createNormalUserCommand: CreateNormalUserCommand): NormalUser {
        with(createNormalUserCommand) {
            if (normalUserRepository.search(SearchNormalUserQuery(email)).isEmpty()) {
                return normalUserRepository.save(
                    NormalUser(email = email, password = password, nickname = nickname)
                )
            }

            throw CustomException(ErrorCode.EXISTS_ENTITY)
        }
    }
}
