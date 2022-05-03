package com.example.user.adpter.out

import com.example.exception.CustomException
import com.example.exception.ErrorCode
import com.example.user.application.port.out.NormalUserRepository
import com.example.user.domain.model.NormalUser
import com.example.user.domain.model.NormalUserEntity
import com.example.user.domain.model.UserStatus
import org.springframework.stereotype.Repository
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Repository
internal class NormalUserRepositoryImpl(private val normalUserDao: NormalUserDao) : NormalUserRepository {

    override fun save(normalUser: NormalUser): NormalUserEntity {
        return normalUserDao.save(NormalUserEntity.from(normalUser))
    }

    override fun findById(id: UUID): NormalUserEntity {
        return normalUserDao.findById(id).orElseThrow { throw CustomException(ErrorCode.ENTITY_NOT_FOUND) }
    }

    override fun find(email: String?, nickname: String?, status: UserStatus?): List<NormalUserEntity> {
        var findNormalUsers = emailFilter(normalUserDao.findAll(), email)
        findNormalUsers = nicknameFilter(findNormalUsers, nickname)
        findNormalUsers = statusFilter(findNormalUsers, status)
        return findNormalUsers
    }

    private fun emailFilter(findNormalUsers: List<NormalUserEntity>, email: String?): List<NormalUserEntity> {
        return if (email == null) findNormalUsers
        else findNormalUsers.filter { it.email == email }
    }

    private fun nicknameFilter(findNormalUsers: List<NormalUserEntity>, nickname: String?): List<NormalUserEntity> {
        return if (nickname == null) findNormalUsers
        else findNormalUsers.filter { it.nickname == nickname }
    }

    private fun statusFilter(findNormalUsers: List<NormalUserEntity>, status: UserStatus?): List<NormalUserEntity> {
        return if (status == null) findNormalUsers
        else findNormalUsers.filter { it.status == status }
    }
}
