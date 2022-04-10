package com.example.user.application.port.`in`

import com.example.user.domain.model.NormalUser

interface CreateNormalUserUseCase {

    fun createNormalUser(createNormalUserCommand: CreateNormalUserCommand): NormalUser
}

data class CreateNormalUserCommand(val email: String, val password: String, val nickname: String)
