package com.example.user.domain.model

import java.util.*

/**
 * 일반 유저
 */
class NormalUser(
    override val id: UUID = UUID.randomUUID(),
    override val email: String,
    password: String,
    nickname: String
) : User.Editor {

    override var password: String = ""
    override var nickname: String = ""
    override var status: UserStatus = UserStatus.REGISTERED

    init {
        this.password = password
        this.nickname = nickname
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is NormalUser) return false

        if (id != other.id) return false
        if (email != other.email) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + email.hashCode()
        return result
    }
}
