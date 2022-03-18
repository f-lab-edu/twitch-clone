package com.example.domain.user.entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("[회원] 일반")
internal class UserTest {

    @DisplayName("email, password, nickName로 일반회원을 생성합니다")
    @ParameterizedTest
    @CsvSource(
        value = ["test@gmail.com,password01,mario"]
    )
    fun `create user by emailAndNicknameAndPassword`(email: String, password: String, nickName: String) {
        // given

        // when
        val user = User(email = email, nickName = nickName, password = password)

        // then
        assertAll(
            { assertThat(user.id).isNotNull() },
            { assertThat(user.email).isNotBlank },
            { assertThat(user.status).isEqualTo(UserStatus.REGISTERED) }
        )
    }
}