package com.example.user.application.service

import com.example.user.application.port.`in`.CreateUserCommand
import com.example.user.application.port.`in`.CreateUserUseCase
import com.example.user.application.port.out.UserRepository
import com.example.user.domain.entity.User

class CreateUserService(private val userRepository: UserRepository) : CreateUserUseCase {

    override fun createUser(createUserCommand: CreateUserCommand): User {
        return with(createUserCommand) {
            userRepository.save(
                User(email = email, password = password, nickname = nickname)
            )
        }
    }
}
