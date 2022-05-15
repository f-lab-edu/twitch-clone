package com.example.user.adpter.out

import com.example.user.domain.model.StreamerUserEntity
import com.example.user.domain.model.StreamerUserStatus
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

internal interface StreamerUserDao : JpaRepository<StreamerUserEntity, UUID> {
    fun findAllByStreamerStatus(streamerUserStatus: StreamerUserStatus): List<StreamerUserEntity>
}
