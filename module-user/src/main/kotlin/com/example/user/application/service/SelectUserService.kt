package com.example.user.application.service

import com.example.user.application.port.`in`.SelectUserUseCase
import com.example.user.application.port.out.SearchUserQuery
import com.example.user.application.port.out.UserRepository
import com.example.user.domain.model.User

internal class SelectUserService(private val userRepository: UserRepository) : SelectUserUseCase {
    override fun selectUsers(searchUserQuery: SearchUserQuery): List<User> {
        return userRepository.search(searchUserQuery)
    }
}
