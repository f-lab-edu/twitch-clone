package com.example.user.application.service

import com.example.user.application.port.`in`.CreateUserCommand
import com.example.user.domain.entity.StreamerUser
import com.example.user.domain.entity.StreamerUserStatus
import com.example.user.domain.entity.User
import com.example.user.domain.entity.UserStatus
import com.example.user.util.MockUserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("[회원] 생성")
internal class CreateUserServiceTest {

    private lateinit var mockUserRepository: MockUserRepository
    private lateinit var createUserService: CreateUserService

    @BeforeEach
    fun beforeEach() {
        mockUserRepository = MockUserRepository()
        createUserService = CreateUserService(mockUserRepository)
    }

    @DisplayName("CreateUserCommand로 일반회원을 생성 합니다")
    @ParameterizedTest
    @MethodSource
    fun `create user by createUserCommand`(createUserCommand: CreateUserCommand) {
        // when
        val user = createUserService.createUser(createUserCommand)

        // then
        val findUser = mockUserRepository.findById(user.id)

        assertAll(
            { assertThat(findUser.id).isEqualTo(user.id) },
            { assertThat(findUser.email).isEqualTo(user.email) },
            { assertThat(findUser.status).isEqualTo(UserStatus.REGISTERED) },
            { assertThat(findUser.status).isEqualTo(user.status) }
        )
    }

    @DisplayName("user, streamerNickname으로 스트리머회원을 생성 합니다")
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

    companion object {
        @JvmStatic
        fun `create user by createUserCommand`(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(CreateUserCommand(email = "test@gmail.com", password = "password01", nickname = "mario"))
            )
        }

        @JvmStatic
        fun `create streamerUser by userAndStreamerNickname`(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(User(email = "test@gmail.com", password = "password01", nickname = "mario"), "koopa")
            )
        }
    }
}
