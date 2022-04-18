package com.example.user.application.port.`in`.normal

import com.example.user.application.port.out.NormalUserRepository
import com.example.user.application.service.normal.CreateNormalUserService
import com.example.user.domain.model.NormalUser

interface CreateNormalUserUseCase {

    fun createNormalUser(createNormalUserCommand: CreateNormalUserCommand): NormalUser

    companion object {
        fun create(normalUserRepository: NormalUserRepository): CreateNormalUserUseCase = CreateNormalUserService(normalUserRepository)
    }
}

data class CreateNormalUserCommand(val email: String, val password: String, val nickname: String)
