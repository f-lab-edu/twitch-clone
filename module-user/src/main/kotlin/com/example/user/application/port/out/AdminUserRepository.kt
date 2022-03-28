package com.example.user.application.port.out

import com.example.user.domain.model.AdminUser
import java.util.*

interface AdminUserRepository {
    fun findById(id: UUID) : AdminUser?

    fun save(adminUser: AdminUser): AdminUser
}
