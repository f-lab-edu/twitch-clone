package com.example.user.adpter.out

import com.example.user.domain.model.AdminUserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

internal interface AdminUserDao : JpaRepository<AdminUserEntity, UUID>
