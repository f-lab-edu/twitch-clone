package com.example.user.adpter.out

import com.example.exception.CustomException
import com.example.exception.ErrorCode
import com.example.user.application.port.out.AdminUserRepository
import com.example.user.domain.model.AdminUser
import com.example.user.domain.model.AdminUserEntity
import org.springframework.stereotype.Repository
import java.util.*

@Repository
internal class AdminUserRepositoryImpl(
    private val adminUserDao: AdminUserDao,
    private val normalUserRepositoryImpl: NormalUserRepositoryImpl,
) : AdminUserRepository {
    override fun findById(id: UUID): AdminUser {
        return adminUserDao.findById(id).orElseThrow { throw CustomException(ErrorCode.ENTITY_NOT_FOUND) }
    }

    override fun existsById(id: UUID): Boolean {
        return adminUserDao.findById(id).isPresent
    }

    override fun save(adminUser: AdminUser): AdminUserEntity {
        val normalUserEntity = normalUserRepositoryImpl.findById(adminUser.id)
        return adminUserDao.save(AdminUserEntity.from(adminUser, normalUserEntity))
    }
}
