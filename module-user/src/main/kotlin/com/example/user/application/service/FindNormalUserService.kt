package com.example.user.application.service

import com.example.user.application.port.`in`.FindNormalUserUseCase
import com.example.user.application.port.out.SearchUserQuery
import com.example.user.application.port.out.NormalUserRepository
import com.example.user.domain.model.NormalUser

internal class FindNormalUserService(private val normalUserRepository: NormalUserRepository) : FindNormalUserUseCase {
    override fun findNormalUsers(searchUserQuery: SearchUserQuery): List<NormalUser> {
        return normalUserRepository.search(searchUserQuery)
    }
}
