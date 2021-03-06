package com.example.user.domain.model

import java.util.*

/**
 * 스트리머 유저
 */
open class StreamerUser(
    override val id: UUID,
    override val email: String,
    password: String,
    nickname: String,
    open var streamerNickname: String,
    open var subscriptionCost: Int
) : User.Editor {
    override var password: String = ""
    override var status: UserStatus = UserStatus.REGISTERED
    override var nickname: String = ""

    open val streamerStatus: StreamerUserStatus
        get() {
            return streamerStatusVar
        }

    init {
        this.password = password
        this.nickname = nickname
    }

    private var streamerStatusVar: StreamerUserStatus = StreamerUserStatus.PENDING

    fun register() {
        this.streamerStatusVar = StreamerUserStatus.REGISTERED
    }

    fun reject() {
        this.streamerStatusVar = StreamerUserStatus.REJECTED
    }

    fun suspend() {
        this.streamerStatusVar = StreamerUserStatus.SUSPENDED
    }

}
