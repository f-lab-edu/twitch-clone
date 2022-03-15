package com.example.domain.user.service

import com.example.domain.user.entity.StreamerUser
import com.example.domain.user.entity.User

class CreateUserService {

    fun createUser(email: String, nickName: String): User {
        return User(email, nickName)
    }

    fun createStreamerUser(user: User, streamerName: String): StreamerUser {
        return StreamerUser(user, streamerName)
    }
}
