package com.example.user.application.port.`in`

import com.example.user.domain.model.NormalUser

interface CreateNormalUserUseCase {

    fun createNormalUser(createUserCommand: CreateUserCommand): NormalUser
}

data class CreateUserCommand(val email: String, val password: String, val nickname: String)
