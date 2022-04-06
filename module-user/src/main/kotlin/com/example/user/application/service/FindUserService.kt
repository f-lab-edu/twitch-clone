package com.example.user.application.service

import com.example.user.application.port.`in`.FindUserUseCase
import com.example.user.application.port.out.SearchUserQuery
import com.example.user.application.port.out.NormalUserRepository
import com.example.user.domain.model.NormalUser

internal class FindUserService(private val normalUserRepository: NormalUserRepository) : FindUserUseCase {
    override fun findUsers(searchUserQuery: SearchUserQuery): List<NormalUser> {
        return normalUserRepository.search(searchUserQuery)
    }
}
