package com.example.user.application.service

import com.example.user.application.port.out.SearchUserQuery
import com.example.user.util.MockNormalUserRepository
import com.example.user.util.randomUser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("[유저] 조회")
class FindNormalUserServiceTest {

    private lateinit var findUserService: FindUserService
    private lateinit var mockUserRepository: MockNormalUserRepository

    @BeforeEach
    fun beforeEach() {
        mockUserRepository = MockNormalUserRepository()
        findUserService = FindUserService(mockUserRepository)
    }

    @Test
    @DisplayName("유저 리스트를 email 검색을 통하여 가져온다.")
    fun `select user list by email`() {
        // given
        val user = randomUser()
        mockUserRepository.save(user)

        // when
        val selectUsers = findUserService.findUsers(SearchUserQuery(email = user.email))

        // then
        assertAll(
            { assertThat(selectUsers).isNotEmpty },
            { assertThat(selectUsers[0]).isEqualTo(user) }
        )
    }

    @Test
    @DisplayName("유저 리스트를 nickname 검색을 통하여 가져온다.")
    fun `select user list by nickname`() {
        // given
        val user = randomUser()
        mockUserRepository.save(user)

        // when
        val selectUsers = findUserService.findUsers(SearchUserQuery(nickname = user.nickname))

        // then
        assertAll(
            { assertThat(selectUsers).isNotEmpty },
            { assertThat(selectUsers[0]).isEqualTo(user) }
        )
    }

    @Test
    @DisplayName("유저 리스트를 유저 상태 검색을 통하여 가져온다.")
    fun `select user list by status`() {
        // given
        val user = randomUser()
        mockUserRepository.save(user)

        // when
        val selectUsers = findUserService.findUsers(SearchUserQuery(status = user.status))

        // then
        assertAll(
            { assertThat(selectUsers).isNotEmpty },
            { assertThat(selectUsers[0]).isEqualTo(user) }
        )
    }

    @Test
    @DisplayName("유저 리스트를 유저 이메일, 닉네임, 상태 검색을 통하여 가져온다.")
    fun `select user list by email and nickname and status`() {
        // given
        val user = randomUser()
        mockUserRepository.save(user)

        // when
        val selectUsers = findUserService.findUsers(
            SearchUserQuery(email = user.email, nickname = user.nickname, status = user.status))

        // then
        assertAll(
            { assertThat(selectUsers).isNotEmpty },
            { assertThat(selectUsers[0]).isEqualTo(user) },
        )
    }
}
