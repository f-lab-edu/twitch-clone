package com.example.user.application.port.out

import com.example.user.domain.model.NormalUser
import com.example.user.domain.model.UserStatus
import java.util.*

interface UserRepository {

    fun save(normalUser: NormalUser): NormalUser

    fun findById(id: UUID): NormalUser

    fun search(searchUserQuery: SearchUserQuery): List<NormalUser>
}

class SearchUserQuery(val email: String? = null, val nickname: String? = null, val status: UserStatus? = null)
