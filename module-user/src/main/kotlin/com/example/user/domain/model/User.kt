package com.example.user.domain.model

import java.util.*

interface User {
    val id: UUID
    val email: String
    val password: String
    val nickname: String
    val status: UserStatus

    interface Editor : User {
        override var password: String

        override var nickname: String

        override var status: UserStatus

        fun suspendedUser() {
            this.status = UserStatus.SUSPENDED
        }

        fun updatePassword(initPassword: String) {
            this.password = initPassword
        }

    }
}
