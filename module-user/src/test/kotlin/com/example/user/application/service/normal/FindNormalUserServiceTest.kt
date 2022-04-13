package com.example.user.application.service.normal

import com.example.user.application.port.`in`.normal.FindNormalUserQuery
import com.example.user.util.MockNormalUserRepository
import com.example.user.util.TestUserGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("[일반 유저] 조회")
class FindNormalUserServiceTest {

    private lateinit var findUserService: FindNormalUserService
    private lateinit var mockUserRepository: MockNormalUserRepository

    @BeforeEach
    fun beforeEach() {
        mockUserRepository = MockNormalUserRepository()
        findUserService = FindNormalUserService(mockUserRepository)
    }

    @Test
    @DisplayName("일반 유저 리스트를 email 검색을 통하여 가져온다.")
    fun `select normal user list by email`() {
        // given
        val user = TestUserGenerator.normalUser()
        mockUserRepository.save(user)

        // when
        val selectUsers = findUserService.findNormalUsers(FindNormalUserQuery(email = user.email))

        // then
        assertAll(
            { assertThat(selectUsers).isNotEmpty },
            { assertThat(selectUsers[0]).isEqualTo(user) }
        )
    }

    @Test
    @DisplayName("일반 유저 리스트를 nickname 검색을 통하여 가져온다.")
    fun `select normal user list by nickname`() {
        // given
        val user = TestUserGenerator.normalUser()
        mockUserRepository.save(user)

        // when
        val selectUsers = findUserService.findNormalUsers(FindNormalUserQuery(nickname = user.nickname))

        // then
        assertAll(
            { assertThat(selectUsers).isNotEmpty },
            { assertThat(selectUsers[0]).isEqualTo(user) }
        )
    }

    @Test
    @DisplayName("일반 유저 리스트를 유저 상태 검색을 통하여 가져온다.")
    fun `select normal user list by status`() {
        // given
        val user = TestUserGenerator.normalUser()
        mockUserRepository.save(user)

        // when
        val selectUsers = findUserService.findNormalUsers(FindNormalUserQuery(status = user.status))

        // then
        assertAll(
            { assertThat(selectUsers).isNotEmpty },
            { assertThat(selectUsers[0]).isEqualTo(user) }
        )
    }

    @Test
    @DisplayName("일반 유저 리스트를 유저 이메일, 닉네임, 상태 검색을 통하여 가져온다.")
    fun `select normal user list by email and nickname and status`() {
        // given
        val user = TestUserGenerator.normalUser()
        mockUserRepository.save(user)

        // when
        val selectUsers = findUserService.findNormalUsers(
            FindNormalUserQuery(email = user.email, nickname = user.nickname, status = user.status)
        )

        // then
        assertAll(
            { assertThat(selectUsers).isNotEmpty },
            { assertThat(selectUsers[0]).isEqualTo(user) },
        )
    }
}
