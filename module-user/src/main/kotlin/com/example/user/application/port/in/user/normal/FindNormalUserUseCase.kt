package com.example.user.application.port.`in`.user.normal

import com.example.user.application.port.out.SearchNormalUserQuery
import com.example.user.domain.model.NormalUser

interface FindNormalUserUseCase {
    fun findNormalUsers(searchNormalUserQuery: SearchNormalUserQuery): List<NormalUser>
}
