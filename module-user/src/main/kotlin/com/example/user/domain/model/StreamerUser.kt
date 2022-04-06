package com.example.user.domain.model

import java.util.*

/**
 * 스트리머 회원
 */
class StreamerUser(
    override val id: UUID,
    override val email: String,
    password: String,
    nickname: String,
    var streamerNickname: String,
) : User.Editor {
    override var password: String = ""
    override var status: UserStatus = UserStatus.REGISTERED
    override var nickname: String = ""

    val streamerStatus: StreamerUserStatus
        get() {
            return streamerStatusVar
        }

    init {
        this.password = password
        this.nickname = nickname
    }

    var streamerStatusVar: StreamerUserStatus = StreamerUserStatus.PENDING

    fun registeredStreamer() {
        this.streamerStatusVar = StreamerUserStatus.REGISTERED
    }

    fun rejectStreamerUser() {
        this.streamerStatusVar = StreamerUserStatus.REJECTED
    }

    fun suspendStreamer() {
        this.streamerStatusVar = StreamerUserStatus.SUSPENDED
    }

}