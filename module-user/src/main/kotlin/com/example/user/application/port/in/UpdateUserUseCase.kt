package com.example.user.application.port.`in`

import com.example.user.domain.model.User
import java.util.*

interface UpdateUserUseCase {

    fun updateUser(updateUserCommand: UpdateUserCommand): User

}

data class UpdateUserCommand(val id: UUID, val nickname: String?)
