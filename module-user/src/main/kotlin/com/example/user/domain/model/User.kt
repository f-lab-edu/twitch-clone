package com.example.user.domain.model

import com.example.model.Editable
import java.util.*

interface User : Editable<User> {
    val id: UUID
    val email: String
    val password: String
    val nickname: String
    val status: UserStatus

    override fun edit(): Editor

    interface Editor : User {
        override var password: String

        override var nickname: String

        override var status: UserStatus

        override fun edit(): Editor = this

        fun suspendedUser() {
            this.status = UserStatus.SUSPENDED
        }

        fun updatePassword(initPassword: String) {
            this.password = initPassword
        }

    }
}
