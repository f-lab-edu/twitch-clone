package com.example.user.application.port.out

import com.example.user.domain.entity.User
import java.util.*

interface UserRepository {

    fun save(user: User): User

    fun findById(id: UUID): User
}
