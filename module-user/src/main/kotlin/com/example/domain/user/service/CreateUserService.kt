package com.example.domain.user.service

import com.example.domain.user.entity.StreamerUser
import com.example.domain.user.entity.User
import com.example.domain.user.repository.UserRepository

class CreateUserService(private val userRepository: UserRepository) {

    fun createUser(email: String, password: String, nickname: String): User {
        return userRepository.save(
            User(email = email, password = password, nickname = nickname)
        )
    }

    fun createStreamerUser(user: User, streamerNickname: String): StreamerUser {
        return StreamerUser(user, streamerNickname)
    }
}
