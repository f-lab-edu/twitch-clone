package com.example.user.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.*
import java.util.stream.Stream

@DisplayName("[어드민 유저]")
internal class AdminUserTest {

    @DisplayName("일반 유저 정보(id, email, password, nickname)와 admin nickname으로 어드민 유저를 생성합니다")
    @ParameterizedTest
    @MethodSource
    fun `create adminUser by user info and admin nickname`(
        id: UUID, email: String, password: String,
        nickname: String, adminNickname: String
    ) {
        // when
        val admin = AdminUser(id, email, password, nickname, adminNickname)

        // then
        assertAll (
            { assertThat(admin.id).isEqualTo(id) },
            { assertThat(admin.email).isEqualTo(email) },
            { assertThat(admin.nickname).isEqualTo(nickname) },
            { assertThat(admin.adminNickname).isEqualTo(adminNickname) },
        )
    }

    companion object {
        @JvmStatic
        fun `create adminUser by user info and admin nickname`(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(UUID.randomUUID(), "test@gmail.com", "passsword01", "user", "admin")
            )
        }
    }
}
