package com.example.user.adpter.out

import com.example.user.domain.model.NormalUserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

internal interface NormalUserDao : JpaRepository<NormalUserEntity, UUID> {
    fun findAllByIdIn(ids: List<UUID>): MutableList<NormalUserEntity>
}
