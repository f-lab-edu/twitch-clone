package com.example.user.application.port.`in`

import com.example.user.domain.entity.User
import java.util.*

interface UpdateUserPasswordUseCase {

    fun updateUserPassword(updateUserPasswordCommand: UpdateUserPasswordCommand): User
}

class UpdateUserPasswordCommand(val id: UUID, val password: String)
