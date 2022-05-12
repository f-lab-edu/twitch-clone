package com.example.user.util

import com.example.exception.CustomException
import com.example.exception.ErrorCode
import com.example.user.application.port.out.AdminUserRepository
import com.example.user.domain.model.AdminUser
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class MockAdminUserRepository : AdminUserRepository {

    private val adminUsers = ConcurrentHashMap<UUID, AdminUser>()

    override fun findById(id: UUID): AdminUser {
        return this.adminUsers[id] ?: throw CustomException(ErrorCode.ENTITY_NOT_FOUND)
    }

    override fun existsById(id: UUID): Boolean {
        return this.adminUsers[id] != null
    }

    override fun save(adminUser: AdminUser): AdminUser {
        this.adminUsers[adminUser.id] = adminUser
        return adminUser
    }
}
