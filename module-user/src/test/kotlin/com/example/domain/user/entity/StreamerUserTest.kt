package com.example.domain.user.entity

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
        fun `create streamerUser by userAndStreamerName`(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(User(email = "test@gmail.com", password = "passsword01", nickName = "mario"), "koopa")
            )
        }
    }

    @DisplayName("user, streamerName으로 스트리머회원을 생성합니다")
    @ParameterizedTest
    @MethodSource
    fun `create streamerUser by userAndStreamerName`(user: User, streamerName: String) {
        // given

        // when
        val streamer = StreamerUser(user, streamerName)

        // then
        assertAll(
            { assertThat(streamer.user.status).isEqualTo(UserStatus.REGISTERED) },
            { assertThat(streamer.status).isEqualTo(StreamerUserStatus.PENDING) }
        )
    }
}
