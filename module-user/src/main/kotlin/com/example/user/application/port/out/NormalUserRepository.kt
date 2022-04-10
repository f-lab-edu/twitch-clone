package com.example.user.application.port.out

import com.example.user.domain.model.NormalUser
import com.example.user.domain.model.UserStatus
import java.util.*

interface NormalUserRepository {

    fun save(normalUser: NormalUser): NormalUser

    fun findById(id: UUID): NormalUser

    fun search(email: String? = null, nickname: String? = null, status: UserStatus? = null): List<NormalUser>
}
