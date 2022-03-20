package com.example.user.application.port.`in`

import com.example.user.domain.entity.User

interface CreateUserUseCase {

    fun createUser(createUserCommand: CreateUserCommand): User
}

class CreateUserCommand(val email: String, val password: String, val nickname: String)
