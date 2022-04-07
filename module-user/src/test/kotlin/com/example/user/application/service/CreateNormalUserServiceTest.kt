package com.example.user.application.service

import com.example.exception.CustomException
import com.example.exception.ErrorCode
import com.example.user.application.port.`in`.CreateUserCommand
import com.example.user.domain.model.UserStatus
import com.example.user.util.MockNormalUserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("[일반 유저] 생성")
internal class CreateNormalUserServiceTest {

    private lateinit var mockUserRepository: MockNormalUserRepository
    private lateinit var createNormalUserService: CreateNormalUserService

    @BeforeEach
    fun beforeEach() {
        mockUserRepository = MockNormalUserRepository()
        createNormalUserService = CreateNormalUserService(mockUserRepository)
    }

    @DisplayName("CreateUserCommand로 일반유저를 생성 합니다")
    @ParameterizedTest
    @MethodSource
    fun `create normal user by createUserCommand`(createUserCommand: CreateUserCommand) {
        // when
        val user = createNormalUserService.createNormalUser(createUserCommand)

        // then
        val findUser = mockUserRepository.findById(user.id)

        assertAll(
            { assertThat(findUser.id).isEqualTo(user.id) },
            { assertThat(findUser.email).isEqualTo(user.email) },
            { assertThat(findUser.status).isEqualTo(UserStatus.REGISTERED) },
            { assertThat(findUser.status).isEqualTo(user.status) }
        )
    }

    @DisplayName("이미 존재하는 email의 일반유저를 생성하려고 하면 CustomException이 발생합니다")
    @ParameterizedTest
    @MethodSource("create normal user by createUserCommand")
    fun `create user by existsNickname caused customException`(createUserCommand: CreateUserCommand) {
        // given
        createNormalUserService.createNormalUser(createUserCommand)

        // when
        val exception = assertThrows(CustomException::class.java) {
            createNormalUserService.createNormalUser(createUserCommand)
        }

        // then
        assertThat(exception.errorCode).isEqualTo(ErrorCode.EXISTS_ENTITY)
    }

    companion object {
        @JvmStatic
        fun `create normal user by createUserCommand`(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(CreateUserCommand(email = "test@gmail.com", password = "password01", nickname = "mario"))
            )
        }
    }
}
