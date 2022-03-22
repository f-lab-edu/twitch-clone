package com.example.user.application.service

import com.example.user.application.port.`in`.UpdateUserPasswordCommand
import com.example.user.util.MockUserRepository
import com.example.user.util.randomUser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("[회원] 정보 수정")
internal class UpdateUserPasswordServiceTest {

    private lateinit var mockUserRepository: MockUserRepository
    private lateinit var updateUserPasswordService: UpdateUserPasswordService

    @BeforeEach
    fun beforeEach() {
        mockUserRepository = MockUserRepository()
        updateUserPasswordService = UpdateUserPasswordService(mockUserRepository)
    }

    @DisplayName("회원의 password를 변경합니다")
    @ParameterizedTest
    @CsvSource(
        value = ["password50"]
    )
    fun `update user by password`(newPassword: String) {
        // given
        val user = randomUser()
        mockUserRepository.save(user)

        val updateUserPasswordCommand = UpdateUserPasswordCommand(user.id, newPassword)

        // when
        updateUserPasswordService.updateUserPassword(updateUserPasswordCommand)

        // then
        val findUser = mockUserRepository.findById(updateUserPasswordCommand.id)

        assertAll(
            { assertThat(findUser).isNotNull },

            { assertThat(findUser.id).isEqualTo(updateUserPasswordCommand.id) },
            { assertThat(findUser.password).isEqualTo(newPassword) },
        )
    }
}
