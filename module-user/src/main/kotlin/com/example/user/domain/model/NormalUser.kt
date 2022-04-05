package com.example.user.domain.model

import java.util.*

/**
 * 회원
 */
data class NormalUser(
    val id: UUID = UUID.randomUUID(),
    val email: String,
    val password: String,
    val nickname: String,
    val status: UserStatus = UserStatus.REGISTERED
) {
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
