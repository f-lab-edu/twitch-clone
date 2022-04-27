package com.example.user.adpter.out

import com.example.user.domain.model.NormalUserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface NormalUserDao : JpaRepository<NormalUserEntity, UUID>
