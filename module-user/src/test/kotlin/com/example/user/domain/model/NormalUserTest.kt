package com.example.user.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("[회원] 일반")
internal class NormalUserTest {

    @DisplayName("email, password, nickname로 일반회원을 생성합니다")
    @ParameterizedTest
    @CsvSource(
        value = ["test@gmail.com,password01,mario"]
    )
    fun `create user by emailAndNicknameAndPassword`(email: String, password: String, nickname: String) {
        // when
        val user = NormalUser(email = email, nickname = nickname, password = password)

        // then
        assertAll(
            { assertThat(user.id).isNotNull() },
            { assertThat(user.email).isNotBlank },
            { assertThat(user.status).isEqualTo(UserStatus.REGISTERED) }
        )
    }
}
