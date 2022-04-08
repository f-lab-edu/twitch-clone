package com.example.user.domain.model

import java.util.*

class AdminUser(
    override val id: UUID,
    override val email: String,
    password: String,
    nickname: String,
    val adminNickname: String
) : User.Editor {
    override var status: UserStatus = UserStatus.REGISTERED
    override var nickname: String = ""
    override var password: String = ""

    init {
        this.nickname = nickname
        this.password = password
    }
}
