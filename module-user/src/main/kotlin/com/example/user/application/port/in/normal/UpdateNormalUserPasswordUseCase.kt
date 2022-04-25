package com.example.user.application.port.`in`.normal

import com.example.user.application.port.out.NormalUserRepository
import com.example.user.application.service.normal.UpdateNormalUserPasswordService
import com.example.user.domain.model.NormalUser
import java.util.*

interface UpdateNormalUserPasswordUseCase {

    fun updateNormalUserPassword(updateUserPasswordCommand: UpdateUserPasswordCommand): NormalUser

    companion object {
        fun create(normalUserRepository: NormalUserRepository): UpdateNormalUserPasswordUseCase = UpdateNormalUserPasswordService(normalUserRepository)
    }
}

data class UpdateUserPasswordCommand(val id: UUID, val password: String)
