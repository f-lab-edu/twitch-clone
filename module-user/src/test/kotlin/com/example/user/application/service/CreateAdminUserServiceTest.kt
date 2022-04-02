package com.example.user.application.service

import com.example.exception.CustomException
import com.example.exception.ErrorCode
import com.example.user.util.MockAdminUserRepository
import com.example.user.util.randomUser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("[어드민 회원] 생성")
internal class CreateAdminUserServiceTest {

    private lateinit var mockAdminUserRepository: MockAdminUserRepository
    private lateinit var createAdminUserService: CreateAdminUserService

    @BeforeEach
    fun beforeEach() {
        mockAdminUserRepository = MockAdminUserRepository()
        createAdminUserService = CreateAdminUserService(mockAdminUserRepository)
    }

    @Test
    @DisplayName("User 정보와 nickname으로 어드민 회원을 생성 합니다")
    fun `create user by createUserCommand`() {
        // when
        val adminUser = createAdminUserService.createAdminUser(randomUser(), "admin")

        // then
        val findAdmin = mockAdminUserRepository.findById(adminUser.id())

        assertAll(
            { assertThat(findAdmin).isNotNull() },
            { assertThat(findAdmin).isEqualTo(adminUser) }
        )
    }

    @Test
    @DisplayName("이미 존재하는 ID의 어드민 회원을 생성하려고 할 경우 CustomException 발생")
    fun `create admin by exists id caused customException`() {
        // given
        val user = randomUser()
        createAdminUserService.createAdminUser(user, "admin")

        // when
        val exception =
            assertThrows(CustomException::class.java) {
                createAdminUserService.createAdminUser(user, "admin")
            }

        // then
        assertThat(exception.errorCode).isEqualTo(ErrorCode.EXISTS_ENTITY)
    }
}
