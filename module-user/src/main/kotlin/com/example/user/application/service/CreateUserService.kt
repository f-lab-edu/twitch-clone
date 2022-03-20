package com.example.user.application.service

import com.example.user.application.port.out.UserRepository
import com.example.user.domain.entity.StreamerUser
import com.example.user.domain.entity.User

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
