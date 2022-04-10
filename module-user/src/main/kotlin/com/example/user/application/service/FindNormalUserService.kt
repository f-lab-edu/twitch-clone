package com.example.user.application.service

import com.example.user.application.port.`in`.user.normal.FindNormalUserUseCase
import com.example.user.application.port.out.NormalUserRepository
import com.example.user.application.port.out.SearchNormalUserQuery
import com.example.user.domain.model.NormalUser

internal class FindNormalUserService(private val normalUserRepository: NormalUserRepository) : FindNormalUserUseCase {
    override fun findNormalUsers(searchNormalUserQuery: SearchNormalUserQuery): List<NormalUser> {
        return normalUserRepository.search(searchNormalUserQuery)
    }
}
