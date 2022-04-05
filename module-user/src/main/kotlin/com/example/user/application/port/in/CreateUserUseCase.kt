package com.example.user.application.port.`in`

import com.example.user.domain.model.User

interface CreateUserUseCase {

    fun createUser(createUserCommand: CreateUserCommand): User
}

data class CreateUserCommand(val email: String, val password: String, val nickname: String)
