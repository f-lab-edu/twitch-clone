package com.example.user.application.port.`in`.normal

import com.example.user.domain.model.User
import java.util.*

interface UpdateNormalUserUseCase {

    fun updateNormalUser(updateUserCommand: UpdateUserCommand): User

}

data class UpdateUserCommand(val id: UUID, val nickname: String?)
