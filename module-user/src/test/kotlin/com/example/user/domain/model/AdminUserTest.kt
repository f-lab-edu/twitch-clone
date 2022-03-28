package com.example.user.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("[회원] 어드민 회원")
internal class AdminUserTest {

    @DisplayName("user, admin nickname으로 어드민 회원을 생성합니다")
    @ParameterizedTest
    @MethodSource
    fun `create adminUser by user and admin nickname`(user: User, adminNickname: String) {
        // when
        val admin = AdminUser(user, adminNickname)

        // then
        assertThat(admin).isEqualTo(AdminUser(user, adminNickname))
    }

    companion object {
        @JvmStatic
        fun `create adminUser by user and admin nickname`(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(User(email = "test@gmail.com", password = "passsword01", nickname = "user"), "admin")
            )
        }
    }
}
