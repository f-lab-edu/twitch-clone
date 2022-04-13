package com.example.user.application.service.normal

import com.example.user.application.port.`in`.normal.UpdateUserPasswordCommand
import com.example.user.util.MockNormalUserRepository
import com.example.user.util.TestUserGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("[일반 유저] 비밀번호 수정")
internal class UpdateNormalUserPasswordServiceTest {

    private lateinit var mockUserRepository: MockNormalUserRepository
    private lateinit var updateNormalUserPasswordService: UpdateNormalUserPasswordService

    @BeforeEach
    fun beforeEach() {
        mockUserRepository = MockNormalUserRepository()
        updateNormalUserPasswordService = UpdateNormalUserPasswordService(mockUserRepository)
    }

    @DisplayName("일반 유저의 password를 변경합니다")
    @ParameterizedTest
    @CsvSource(
        value = ["password50"]
    )
    fun `update normal user by password`(newPassword: String) {
        // given
        val user = TestUserGenerator.normalUser()
        mockUserRepository.save(user)

        val updateUserPasswordCommand = UpdateUserPasswordCommand(user.id, newPassword)

        // when
        updateNormalUserPasswordService.updateNormalUserPassword(updateUserPasswordCommand)

        // then
        val findUser = mockUserRepository.findById(updateUserPasswordCommand.id)

        assertAll(
            { assertThat(findUser).isNotNull },

            { assertThat(findUser.id).isEqualTo(updateUserPasswordCommand.id) },
            { assertThat(findUser.password).isEqualTo(newPassword) },
        )
    }

    @Test
    @DisplayName("일반 유저의 password를 초기화 합니다")
    fun `user normal password init`() {
        // given
        val user = TestUserGenerator.normalUser()
        mockUserRepository.save(user)
        val userId = user.id

        // when
        updateNormalUserPasswordService.initNormalUserPassword(userId)

        // then
        val findUser = mockUserRepository.findById(userId)
        assertThat(findUser.password).isEqualTo("test1234")
    }
}
