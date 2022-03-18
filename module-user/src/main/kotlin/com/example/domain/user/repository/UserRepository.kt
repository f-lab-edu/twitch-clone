package com.example.domain.user.repository

import com.example.domain.user.entity.User
import java.util.*

interface UserRepository {

    fun save(user: User): User

    fun findById(id: UUID): User
}
