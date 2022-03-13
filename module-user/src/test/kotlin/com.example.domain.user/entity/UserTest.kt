package com.example.domain.user.entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("[회원] 일반")
internal class UserTest {

    @DisplayName("email, nickName으로 일반회원을 생성합니다")
    @ParameterizedTest
    @CsvSource(
        value = ["test@gmail.com,mario"]
    )
    fun `create user by emailAndNickname`(email: String, nickName: String) {
        // given

        // when
        val user = User(email, nickName)

        // then
        assertAll(
            { assertThat(user.email).isNotBlank },
            { assertThat(user.status).isEqualTo(UserStatus.REGISTERED) }
        )
    }
}
