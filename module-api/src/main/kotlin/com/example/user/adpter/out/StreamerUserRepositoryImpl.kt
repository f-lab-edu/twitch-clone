package com.example.user.adpter.out

import com.example.exception.CustomException
import com.example.exception.ErrorCode
import com.example.user.application.port.out.StreamerUserRepository
import com.example.user.domain.model.StreamerUser
import com.example.user.domain.model.StreamerUserEntity
import com.example.user.domain.model.StreamerUserStatus
import org.springframework.stereotype.Repository
import java.util.*

@Repository
internal class StreamerUserRepositoryImpl(
    private val streamerUserDao: StreamerUserDao,
    private val streamerUserDynamicDao: StreamerUserDynamicDao,
    private val normalUserDao: NormalUserDao,
    private val normalUserRepositoryImpl: NormalUserRepositoryImpl,
) : StreamerUserRepository {
    override fun save(streamerUser: StreamerUser): StreamerUserEntity {
        val normalUserEntity = normalUserRepositoryImpl.findById(streamerUser.id)
        return streamerUserDao.save(StreamerUserEntity.from(streamerUser, normalUserEntity))
    }

    override fun findById(id: UUID): StreamerUser {
        return streamerUserDao.findById(id).orElseThrow { throw CustomException(ErrorCode.ENTITY_NOT_FOUND) }
    }

    override fun existsById(id: UUID): Boolean {
        return streamerUserDao.findById(id).isPresent
    }

    override fun findAllByStatus(streamerUserStatus: StreamerUserStatus): List<StreamerUser> {
        return streamerUserDao.findAllByStreamerStatus(streamerUserStatus)
    }

    override fun saveAll(streamerUsers: List<StreamerUser>): List<StreamerUserEntity> {
        val streamerUserIds = streamerUsers.map { it.id }
        val normalUserEntityMap = normalUserDao.findAllByIdIn(streamerUserIds).associateBy({ it.id }, { it })
        return streamerUserDao.saveAll(StreamerUserEntity.fromList(streamerUsers, normalUserEntityMap))
    }

    override fun findStreamers(streamerNickname: String?, streamerUserStatus: StreamerUserStatus?): List<StreamerUser> {
        return streamerUserDynamicDao.findStreamers(streamerNickname, streamerUserStatus)
    }
}
