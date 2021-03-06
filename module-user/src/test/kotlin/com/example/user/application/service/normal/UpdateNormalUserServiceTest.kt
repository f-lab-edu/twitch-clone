package com.example.user.application.service.normal

import com.example.exception.CustomException
import com.example.exception.ErrorCode
import com.example.user.application.port.`in`.normal.UpdateUserCommand
import com.example.user.domain.model.UserStatus
import com.example.user.util.MockNormalUserRepository
import com.example.user.util.TestUserGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.util.*

@DisplayName("[일반 유저] 정보 수정")
internal class UpdateNormalUserServiceTest {

    private lateinit var mockUserRepository: MockNormalUserRepository
    private lateinit var updateUserService: UpdateNormalUserService

    @BeforeEach
    fun beforeEach() {
        mockUserRepository = MockNormalUserRepository()
        updateUserService = UpdateNormalUserService(mockUserRepository)
    }

    @DisplayName("일반 유저의 nickname을 luigi로 변경합니다")
    @ParameterizedTest
    @CsvSource(
        value = ["luigi"]
    )
    fun `update user by nickname`(newNickname: String) {
        // given
        val user = TestUserGenerator.normalUser()
        mockUserRepository.save(user)

        val updateUserCommand = UpdateUserCommand(user.id, newNickname)

        // when
        updateUserService.updateNormalUser(updateUserCommand)

        // then
        val findUser = mockUserRepository.findById(updateUserCommand.id)

        assertAll(
            { assertThat(findUser).isNotNull },

            { assertThat(findUser.id).isEqualTo(updateUserCommand.id) },
            { assertThat(findUser.nickname).isEqualTo(newNickname) },
        )
    }

    @DisplayName("존재하지 않는 일반 유저를 수정하려고 하면 CustomException이 발생합니다")
    @ParameterizedTest
    @CsvSource(
        value = ["luigi"]
    )
    fun `update notExistUser caused CustomException`(newNickname: String) {
        // given
        val updateUserCommand = UpdateUserCommand(UUID.randomUUID(), newNickname)

        // when
        val exception = assertThrows(CustomException::class.java) {
            updateUserService.updateNormalUser(updateUserCommand)
        }

        // then
        assertThat(exception.errorCode).isEqualTo(ErrorCode.ENTITY_NOT_FOUND)
    }

    @Test
    @DisplayName("일반 유저의 상태를 정지 상태로 변경한다.")
    fun `update user status suspense`() {
        // given
        val user = TestUserGenerator.normalUser()
        mockUserRepository.save(user)

        // when
        updateUserService.suspendNormalUser(user.id)

        // then
        val findUser = mockUserRepository.findById(user.id)

        assertAll(
            { assertThat(findUser).isNotNull },
            { assertThat(findUser.status).isEqualTo(UserStatus.SUSPENDED) },
        )
    }
}
