package com.example.user.adpter.out

import com.example.user.application.port.out.AdminUserRepository
import com.example.user.domain.model.AdminUser
import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * TODO mysql로 변경합니다.
 */
class AdminUserRepositoryImpl : AdminUserRepository {

    private val adminUsers = ConcurrentHashMap<UUID, AdminUser>()

    override fun findById(id: UUID): AdminUser? {
        return this.adminUsers[id]
    }

    override fun save(adminUser: AdminUser): AdminUser {
        this.adminUsers[adminUser.id] = adminUser
        return adminUser
    }
}
