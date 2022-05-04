package com.example.user.adpter.out.repository

import com.example.exception.CustomException
import com.example.exception.ErrorCode
import com.example.user.adpter.out.AdminUserRepositoryImpl
import com.example.user.adpter.out.NormalUserRepositoryImpl
import com.example.user.config.TestBeanConfig
import com.example.user.domain.model.AdminUser
import com.example.user.domain.model.NormalUser
import com.example.user.util.TestUserGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import java.util.*


@DataJpaTest
@Import(value = [TestBeanConfig::class])
class AdminUserRepositoryImplTest {

    private lateinit var normalUser : NormalUser
    private lateinit var adminUser : AdminUser

    @Autowired
    private lateinit var normalUserRepositoryImpl: NormalUserRepositoryImpl
    @Autowired
    private lateinit var adminUserRepositoryImpl: AdminUserRepositoryImpl

    @BeforeEach
    fun setup() {
        normalUser = TestUserGenerator.normalUser()
        normalUserRepositoryImpl.save(normalUser)
        adminUser = TestUserGenerator.adminUser(id = normalUser.id)
    }

    @DisplayName("어드민 유저 정보를 저장한다.")
    @Test
    fun `save admin user`() {
        // when
        val adminUserEntity = adminUserRepositoryImpl.save(adminUser)

        // then
        assertThat(adminUserEntity.id).isEqualTo(adminUser.id)
    }

    @DisplayName("어드민 유저를 ID를 통하여 검색한다.")
    @Test
    fun `select admin user`() {
        // given
        adminUserRepositoryImpl.save(adminUser)

        // when
        val findAdminUser = adminUserRepositoryImpl.findById(adminUser.id)

        // then
        assertThat(findAdminUser.id).isEqualTo(adminUser.id)
    }

    @DisplayName("어드민 유저를 존재하지 않는 ID를 통하여 검색 하면 Custom Exception을 발생 시킨다.")
    @Test
    fun `select admin user if not exists throw exception`() {
        // when
        val exception = assertThrows<CustomException> {
            adminUserRepositoryImpl.findById(UUID.randomUUID())
        }

        // then
        assertThat(exception.errorCode).isEqualTo(ErrorCode.ENTITY_NOT_FOUND)
    }

    @DisplayName("ID에 해당하는 어드민 유저가 존재하는지 확인한다. - 존재할 경우 true 반환")
    @Test
    fun `exists admin user by admin id if exists`() {
        // given
        adminUserRepositoryImpl.save(adminUser)

        // when
        val isExists = adminUserRepositoryImpl.existsById(adminUser.id)

        // then
        assertThat(isExists).isTrue
    }

    @DisplayName("ID에 해당하는 어드민 유저가 존재하는지 확인한다. - 존재하지 않을 경우 false 반환")
    @Test
    fun `exists admin user by admin id if not exists`() {
        // when
        val isExists = adminUserRepositoryImpl.existsById(adminUser.id)

        // then
        assertThat(isExists).isFalse
    }
}
