package com.example.user.application.service.normal

import com.example.user.application.port.`in`.normal.FindNormalUserQuery
import com.example.user.application.port.`in`.normal.FindNormalUserUseCase
import com.example.user.application.port.out.NormalUserRepository
import com.example.user.domain.model.NormalUser

internal class FindNormalUserService(private val normalUserRepository: NormalUserRepository) : FindNormalUserUseCase {

    override fun findNormalUsers(findNormalUserQuery: FindNormalUserQuery): List<NormalUser> {
        return with(findNormalUserQuery) {
            normalUserRepository.find(email, nickname, status)
        }
    }
}
