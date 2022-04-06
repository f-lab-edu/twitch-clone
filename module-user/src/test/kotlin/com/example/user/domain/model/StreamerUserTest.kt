package com.example.user.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.*
import java.util.stream.Stream

@DisplayName("[회원] 스트리머 회원")
internal class StreamerUserTest {

    companion object {
        @JvmStatic
        fun `create streamerUser by userAndStreamerNickname`(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(UUID.randomUUID(), "test@gmail.com", "passsword01", "mario", "koopa")
            )
        }
    }

    @DisplayName("id, email, password, nickname, streamerNickname으로 스트리머 회원을 생성합니다")
    @ParameterizedTest
    @MethodSource
    fun `create streamerUser by userAndStreamerNickname`(
        id: UUID, email: String, password: String,
        nickname: String, streamerNickname: String
    ) {
        // when
        val streamer = StreamerUser(id, email, password, nickname, streamerNickname)

        // then
        assertAll(
            { assertThat(streamer.status).isEqualTo(UserStatus.REGISTERED) },
            { assertThat(streamer.streamerStatus).isEqualTo(StreamerUserStatus.PENDING) }
        )
    }
}
