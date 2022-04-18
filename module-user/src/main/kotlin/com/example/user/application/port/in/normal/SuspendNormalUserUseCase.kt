package com.example.user.application.port.`in`.normal

import com.example.user.application.port.out.NormalUserRepository
import com.example.user.application.service.normal.UpdateNormalUserService
import java.util.*

interface SuspendNormalUserUseCase {

    fun suspendNormalUser(userId: UUID)

    companion object {
        fun create(normalUserRepository: NormalUserRepository): SuspendNormalUserUseCase = UpdateNormalUserService(normalUserRepository)
    }
}
