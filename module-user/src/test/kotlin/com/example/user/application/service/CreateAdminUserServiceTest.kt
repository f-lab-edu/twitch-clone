package com.example.user.application.service

import com.example.exception.CustomException
import com.example.exception.ErrorCode
import com.example.user.application.port.`in`.CreateAdminUserCommand
import com.example.user.util.MockAdminUserRepository
import com.example.user.util.randomCreateAdminUserCommand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("[어드민 유저] 생성")
internal class CreateAdminUserServiceTest {

    private lateinit var mockAdminUserRepository: MockAdminUserRepository
    private lateinit var createAdminUserService: CreateAdminUserService
    private lateinit var createAdminUserCommand: CreateAdminUserCommand

    @BeforeEach
    fun beforeEach() {
        mockAdminUserRepository = MockAdminUserRepository()
        createAdminUserService = CreateAdminUserService(mockAdminUserRepository)
        createAdminUserCommand = randomCreateAdminUserCommand()
    }

    @Test
    @DisplayName("일반 유저 정보와 nickname으로 어드민 유저를 생성 합니다")
    fun `create admin user by createAdminUserCommand`() {
        // when
        val adminUser = createAdminUserService.createAdminUser(createAdminUserCommand)

        // then
        val findAdmin = mockAdminUserRepository.findById(adminUser.id)

        assertAll(
            { assertThat(findAdmin).isNotNull() },
            { assertThat(findAdmin).isEqualTo(adminUser) }
        )
    }

    @Test
    @DisplayName("이미 존재하는 어드민 유저를 생성하려고 할 경우 CustomException 발생")
    fun `create admin user by exists id caused customException`() {
        // given
        createAdminUserService.createAdminUser(createAdminUserCommand)

        // when
        val exception = assertThrows<CustomException> {
            val duplicateAdminUserCommand = randomCreateAdminUserCommand(normalUser = createAdminUserCommand.normalUser)
            createAdminUserService.createAdminUser(duplicateAdminUserCommand)
        }

        // then
        assertThat(exception.errorCode).isEqualTo(ErrorCode.EXISTS_ENTITY)
    }
}
