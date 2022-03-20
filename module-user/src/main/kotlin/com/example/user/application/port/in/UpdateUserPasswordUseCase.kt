package com.example.user.application.port.`in`

import com.example.user.domain.model.User
import java.util.*

interface UpdateUserPasswordUseCase {

    fun updateUserPassword(updateUserPasswordCommand: UpdateUserPasswordCommand): User
}

data class UpdateUserPasswordCommand(val id: UUID, val password: String)
