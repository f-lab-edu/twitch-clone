package com.example.user.adpter.out

import com.example.exception.CustomException
import com.example.exception.ErrorCode
import com.example.user.application.port.out.NormalUserRepository
import com.example.user.domain.model.NormalUser
import com.example.user.domain.model.NormalUserEntity
import com.example.user.domain.model.UserStatus
import org.springframework.stereotype.Repository
import java.util.*

@Repository
internal class NormalUserRepositoryImpl(
    private val normalUserDao: NormalUserDao,
    private val normalUserDynamicDao: NormalUserDynamicDao
) : NormalUserRepository {

    override fun save(normalUser: NormalUser): NormalUserEntity {
        return normalUserDao.save(NormalUserEntity.from(normalUser))
    }

    override fun findById(id: UUID): NormalUserEntity {
        return normalUserDao.findById(id).orElseThrow { throw CustomException(ErrorCode.ENTITY_NOT_FOUND) }
    }

    override fun find(email: String?, nickname: String?, status: UserStatus?): List<NormalUserEntity> {
        return normalUserDynamicDao.find(email, nickname, status)
    }
}
