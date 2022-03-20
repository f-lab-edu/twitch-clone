package com.example.domain.user.service

import com.example.domain.user.entity.StreamerUser
import com.example.domain.user.entity.StreamerUserStatus
import com.example.domain.user.entity.User
import com.example.domain.user.entity.UserStatus
import com.example.domain.user.repository.MockUserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
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

    @DisplayName("email, password, nickname로 일반회원을 생성 합니다")
    @ParameterizedTest
    @CsvSource(
        value = ["test@gmail.com,password01,mario"]
    )
    fun `create user by emailAndNicknameAndPassword`(email: String, password: String, nickname: String) {
        // when
        val user = createUserService.createUser(email, password, nickname)

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
        fun `create streamerUser by userAndStreamerNickname`(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(User(email = "test@gmail.com", password = "password01", nickname = "mario"), "koopa")
            )
        }
    }
}
