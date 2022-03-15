package com.example.domain.user.service

import com.example.domain.user.entity.StreamerUser
import com.example.domain.user.entity.StreamerUserStatus
import com.example.domain.user.entity.User
import com.example.domain.user.entity.UserStatus
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("[회원] 생성")
internal class CreateUserServiceTest {

    private val createUserService: CreateUserService = CreateUserService()

    @DisplayName("email, nickName으로 일반회원을 생성합니다")
    @ParameterizedTest
    @CsvSource(
        value = ["test@gmail.com,mario"]
    )
    fun `create user by emailAndNickname`(email: String, nickName: String) {
        // given

        // when
        val user = createUserService.createUser(email, nickName)

        // then
        assertAll(
            { assertThat(user.email).isNotBlank },
            { assertThat(user.status).isEqualTo(UserStatus.REGISTERED) }
        )
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

    companion object {
        @JvmStatic
        fun `create streamerUser by userAndStreamerName`(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(User("test@gmail.com", "mario"), "koopa")
            )
        }
    }
}

