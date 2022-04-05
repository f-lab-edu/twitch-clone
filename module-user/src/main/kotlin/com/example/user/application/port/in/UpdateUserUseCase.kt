package com.example.user.application.port.`in`

import com.example.user.domain.model.NormalUser
import java.util.*

interface UpdateUserUseCase {

    fun updateUser(updateUserCommand: UpdateUserCommand): NormalUser

    fun suspendUser(userId: UUID)

}

data class UpdateUserCommand(val id: UUID, val nickname: String?)
