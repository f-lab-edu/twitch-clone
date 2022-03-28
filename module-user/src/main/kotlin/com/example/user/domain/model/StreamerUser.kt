package com.example.user.domain.model

import java.util.*

/**
 * 스트리머 회원
 */
class StreamerUser(
    val user: User, // table 구조에서는 userId만 가지지만 객체 관계로 표현합니다.
    val streamerNickname: String,
    val status: StreamerUserStatus = StreamerUserStatus.PENDING
) {
    internal fun id() : UUID {
        return user.id
    }
}
