package com.example.user.application.service

import com.example.exception.CustomException
import com.example.exception.ErrorCode
import com.example.user.application.port.`in`.UpdateUserCommand
import com.example.user.domain.model.UserStatus
import com.example.user.util.MockUserRepository
import com.example.user.util.randomUser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.util.*

@DisplayName("[회원] 정보 수정")
internal class UpdateUserServiceTest {

    private lateinit var mockUserRepository: MockUserRepository
    private lateinit var updateUserService: UpdateUserService

    @BeforeEach
    fun beforeEach() {
        mockUserRepository = MockUserRepository()
        updateUserService = UpdateUserService(mockUserRepository)
    }

    @DisplayName("회원의 nickname을 luigi로 변경합니다")
    @ParameterizedTest
    @CsvSource(
        value = ["luigi"]
    )
    fun `update user by nickname`(newNickname: String) {
        // given
        val user = randomUser()
        mockUserRepository.save(user)

        val updateUserCommand = UpdateUserCommand(user.id, newNickname)

        // when
        updateUserService.updateUser(updateUserCommand)

        // then
        val findUser = mockUserRepository.findById(updateUserCommand.id)

        assertAll(
            { assertThat(findUser).isNotNull },

            { assertThat(findUser.id).isEqualTo(updateUserCommand.id) },
            { assertThat(findUser.nickname).isEqualTo(newNickname) },
        )
    }

    @DisplayName("존재하지 않는 회원을 수정하려고 하면 CustomException이 발생합니다")
    @ParameterizedTest
    @CsvSource(
        value = ["luigi"]
    )
    fun `update notExistUser caused CustomException`(newNickname: String) {
        // given
        val updateUserCommand = UpdateUserCommand(UUID.randomUUID(), newNickname)

        // when
        val exception = assertThrows(CustomException::class.java) { updateUserService.updateUser(updateUserCommand) }

        // then
        assertThat(exception.errorCode).isEqualTo(ErrorCode.ENTITY_NOT_FOUND)
    }

    @Test
    @DisplayName("회원의 상태를 정지 상태로 변경한다.")
    fun `update user status suspense`() {
        // given
        val user = randomUser()
        mockUserRepository.save(user)

        // when
        updateUserService.suspendUser(user.id)

        // then
        val findUser = mockUserRepository.findById(user.id)

        assertAll(
            { assertThat(findUser).isNotNull },
            { assertThat(findUser.status).isEqualTo(UserStatus.SUSPENDED) },
        )
    }
}
