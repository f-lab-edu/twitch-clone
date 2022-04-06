package com.example.user.application.port.`in`

import com.example.user.domain.model.NormalUser
import java.util.*

interface UpdateUserPasswordUseCase {

    fun updateUserPassword(updateUserPasswordCommand: UpdateUserPasswordCommand): NormalUser

}

data class UpdateUserPasswordCommand(val id: UUID, val password: String)
