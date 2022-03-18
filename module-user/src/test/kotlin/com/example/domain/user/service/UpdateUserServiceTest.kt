package com.example.domain.user.service

import com.example.domain.user.repository.MockUserRepository
import com.example.exception.CustomException
import com.example.exception.ErrorCode
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.util.*

@DisplayName("[회원] 정보 수정")
internal class UpdateUserServiceTest {

    private lateinit var mockUserRepository: MockUserRepository
    private lateinit var createUserService: CreateUserService
    private lateinit var updateUserService: UpdateUserService

    @BeforeEach
    fun beforeEach() {
        mockUserRepository = MockUserRepository()
        createUserService = CreateUserService(mockUserRepository)
        updateUserService = UpdateUserService(mockUserRepository)
    }

    @DisplayName("회원의 nickName을 luigi로 변경합니다")
    @ParameterizedTest
    @CsvSource(
        value = ["test@gmail.com,password01,mario,luigi"]
    )
    fun `update user by nickname`(email: String, password: String, nickName: String, newNickName: String) {
        // given
        val user = createUserService.createUser(email, password, nickName)
        val beforeId = user.id

        // when
        updateUserService.updateUser(beforeId, newNickName)

        // then
        val findUser = mockUserRepository.findById(beforeId) ?: fail(ErrorCode.ENTITY_NOT_FOUND.reason)

        assertAll(
            { assertThat(findUser).isNotNull },

            { assertThat(findUser.id).isEqualTo(beforeId) },
            { assertThat(findUser.nickName).isEqualTo(newNickName) },
        )
    }

    @DisplayName("존재하지 않는 회원을 수정하려고 하면 CustomException이 발생합니다")
    @ParameterizedTest
    @CsvSource(
        value = ["luigi"]
    )
    fun `update notExistUser caused CustomException`(newNickName: String) {
        // given

        // when
        val exception = assertThrows(CustomException::class.java) { updateUserService.updateUser(UUID.randomUUID(), newNickName) }

        // then
        assertThat(exception.errorCode).isEqualTo(ErrorCode.ENTITY_NOT_FOUND)
    }
}