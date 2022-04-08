package com.example.user.domain.model

import java.util.*

data class AdminUser(val user: NormalUser, val nickname: String) {
    fun id(): UUID {
        return user.id
    }
}
