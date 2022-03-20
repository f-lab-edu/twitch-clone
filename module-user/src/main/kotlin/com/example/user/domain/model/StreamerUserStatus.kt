package com.example.user.domain.model

/**
 * 스트리머는 등록과 정지만 존재합니다.
 * Streamer는 User의 하위 개념이기 때문에 탈퇴가 존재하지 않습니다.
 */
enum class StreamerUserStatus(val description: String) {
    PENDING("등록대기"),
    REGISTERED("등록"),
    SUSPENSE("정지")
}
