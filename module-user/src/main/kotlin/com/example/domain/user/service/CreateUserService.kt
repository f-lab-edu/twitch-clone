package com.example.domain.user.service

import com.example.domain.user.entity.StreamerUser
import com.example.domain.user.entity.User
import com.example.domain.user.repository.UserRepository

class CreateUserService(private val userRepository: UserRepository) {

    fun createUser(email: String, password: String, nickName: String): User {
        return userRepository.save(
            User(email = email, password = password, nickName = nickName)
        )
    }

    fun createStreamerUser(user: User, streamerName: String): StreamerUser {
        return StreamerUser(user, streamerName)
    }
}
