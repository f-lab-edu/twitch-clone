package com.example.domain.user.service

import com.example.domain.user.repository.MockUserRepository
import com.example.domain.user.util.randomUser
import com.example.exception.CustomException
import com.example.exception.ErrorCode
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
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
        value = ["test@gmail.com,password01,mario,luigi"]
    )
    fun `update user by nickname`(email: String, password: String, nickname: String, newNickname: String) {
        // given
        val user = randomUser()
        mockUserRepository.save(user)

        val beforeId = user.id

        // when
        updateUserService.updateUser(beforeId, newNickname)

        // then
        val findUser = mockUserRepository.findById(beforeId)

        assertAll(
            { assertThat(findUser).isNotNull },

            { assertThat(findUser.id).isEqualTo(beforeId) },
            { assertThat(findUser.nickname).isEqualTo(newNickname) },
        )
    }

    @DisplayName("존재하지 않는 회원을 수정하려고 하면 CustomException이 발생합니다")
    @ParameterizedTest
    @CsvSource(
        value = ["luigi"]
    )
    fun `update notExistUser caused CustomException`(newNickname: String) {
        // when
        val exception = assertThrows(CustomException::class.java) { updateUserService.updateUser(UUID.randomUUID(), newNickname) }

        // then
        assertThat(exception.errorCode).isEqualTo(ErrorCode.ENTITY_NOT_FOUND)
    }

    @DisplayName("회원의 password를 변경합니다")
    @ParameterizedTest
    @CsvSource(
        value = ["test@gmail.com,password01,mario,password50"]
    )
    fun `update user by password`(email: String, password: String, nickname: String, newPassword: String) {
        // given
        val user = randomUser()
        mockUserRepository.save(user)

        val beforeId = user.id

        // when
        updateUserService.updateUserPassword(beforeId, newPassword)

        // then
        val findUser = mockUserRepository.findById(beforeId)

        assertAll(
            { assertThat(findUser).isNotNull },

            { assertThat(findUser.id).isEqualTo(beforeId) },
            { assertThat(findUser.password).isEqualTo(newPassword) },
        )
    }
}
