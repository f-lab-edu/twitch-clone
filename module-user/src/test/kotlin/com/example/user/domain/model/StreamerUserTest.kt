package com.example.user.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("[회원] 스트리머 회원")
internal class StreamerUserTest {

    companion object {
        @JvmStatic
        fun `create streamerUser by userAndStreamerNickname`(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(User(email = "test@gmail.com", password = "passsword01", nickname = "mario"), "koopa")
            )
        }
    }

    @DisplayName("user, streamerNickname으로 스트리머회원을 생성합니다")
    @ParameterizedTest
    @MethodSource
    fun `create streamerUser by userAndStreamerNickname`(user: User, streamerNickname: String) {
        // when
        val streamer = StreamerUser(user, streamerNickname)

        // then
        assertAll(
            { assertThat(streamer.user.status).isEqualTo(UserStatus.REGISTERED) },
            { assertThat(streamer.status).isEqualTo(StreamerUserStatus.PENDING) }
        )
    }
}
