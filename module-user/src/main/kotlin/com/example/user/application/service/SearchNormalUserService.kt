package com.example.user.application.service

import com.example.user.application.port.`in`.user.normal.SearchNormalUserQuery
import com.example.user.application.port.`in`.user.normal.SearchNormalUserUseCase
import com.example.user.application.port.out.NormalUserRepository
import com.example.user.domain.model.NormalUser

internal class SearchNormalUserService(private val normalUserRepository: NormalUserRepository) : SearchNormalUserUseCase {

    override fun searchNormalUsers(searchNormalUserQuery: SearchNormalUserQuery): List<NormalUser> {
        return with(searchNormalUserQuery) {
            normalUserRepository.search(email, nickname, status)
        }
    }
}
