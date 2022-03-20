package com.example.user.application.port.`in`

import com.example.user.domain.entity.User
import java.util.*

interface UpdateUserUseCase {

    fun updateUser(updateUserCommand: UpdateUserCommand): User
}

class UpdateUserCommand(val id: UUID, val nickname: String?)
