package com.example.user.application.port.out

import com.example.user.domain.model.NormalUser
import com.example.user.domain.model.UserStatus
import java.util.*

interface NormalUserRepository {

    fun save(normalUser: NormalUser): NormalUser

    fun findById(id: UUID): NormalUser

    fun search(searchNormalUserQuery: SearchNormalUserQuery): List<NormalUser>
}

class SearchNormalUserQuery(val email: String? = null, val nickname: String? = null, val status: UserStatus? = null)
