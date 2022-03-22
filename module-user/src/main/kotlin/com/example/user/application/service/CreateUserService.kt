package com.example.user.application.service

import com.example.exception.CustomException
import com.example.exception.ErrorCode
import com.example.user.application.port.`in`.CreateUserCommand
import com.example.user.application.port.`in`.CreateUserUseCase
import com.example.user.application.port.out.SearchUserQuery
import com.example.user.application.port.out.UserRepository
import com.example.user.domain.model.User

internal class CreateUserService(private val userRepository: UserRepository) : CreateUserUseCase {

    override fun createUser(createUserCommand: CreateUserCommand): User {
        with(createUserCommand) {
            if (userRepository.search(SearchUserQuery(email)).isEmpty()) {
                return userRepository.save(
                    User(email = email, password = password, nickname = nickname)
                )
            }

            throw CustomException(ErrorCode.EXISTS_ENTITY)
        }
    }
}
