package com.example.user.application.service

import com.example.user.application.port.`in`.UpdateUserPasswordCommand
import com.example.user.application.port.`in`.UpdateUserPasswordUseCase
import com.example.user.application.port.out.UserRepository
import com.example.user.domain.model.User

internal class UpdateUserPasswordService(private val userRepository: UserRepository) : UpdateUserPasswordUseCase {

    override fun updateUserPassword(updateUserPasswordCommand: UpdateUserPasswordCommand): User {
        return with(updateUserPasswordCommand) {
            userRepository.findById(id).let {
                userRepository.save(
                    User(
                        id = it.id, email = it.email,
                        password = password,
                        nickname = it.nickname
                    )
                )
            }
        }
    }
}
