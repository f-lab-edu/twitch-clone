package com.example.user.adpter.out.repository

import com.example.exception.CustomException
import com.example.exception.ErrorCode
import com.example.user.adpter.out.NormalUserRepositoryImpl
import com.example.user.config.TestBeanConfig
import com.example.user.domain.model.NormalUser
import com.example.user.domain.model.UserStatus
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
class NormalUserRepositoryImplTest {

    @Autowired
    private lateinit var normalUserRepositoryImpl: NormalUserRepositoryImpl

    private lateinit var normalUser : NormalUser

    @BeforeEach
    fun setup() {
        normalUser = TestUserGenerator.normalUser()
    }

    @DisplayName("일반 유저 정보를 저장한다.")
    @Test
    fun `save normal user`() {
        // when
        val normalUserEntity = normalUserRepositoryImpl.save(normalUser)

        // then
        assertThat(normalUserEntity.id).isEqualTo(normalUser.id)
    }

    @DisplayName("일반 유저를 ID를 통하여 검색한다.")
    @Test
    fun `select normal user by id`() {
        // given
        normalUserRepositoryImpl.save(normalUser)

        // when
        val findNormalUser = normalUserRepositoryImpl.findById(normalUser.id)

        // then
        assertThat(findNormalUser.id).isEqualTo(normalUser.id)
    }

    @DisplayName("일반 유저를 존재하지 않는 ID를 통하여 검색 하면 Custom Exception을 발생 시킨다.")
    @Test
    fun `select normal user if not exists throw exception`() {
        // when
        val exception = assertThrows<CustomException> {
            normalUserRepositoryImpl.findById(UUID.randomUUID())
        }

        // then
        assertThat(exception.errorCode).isEqualTo(ErrorCode.ENTITY_NOT_FOUND)
    }

    @DisplayName("등록되어 있는 일반 유저 리스트를 가져온다.")
    @Test
    fun `select all normal user`() {
        // given
        val normalUser2 = TestUserGenerator.normalUser()
        normalUserRepositoryImpl.save(normalUser)
        normalUserRepositoryImpl.save(normalUser2)

        // when
        val normalUserEntities = normalUserRepositoryImpl.find()

        // then
        assertThat(normalUserEntities.size).isEqualTo(2)
    }

    @DisplayName("등록되어 있는 일반 유저 리스트를 email 검색을 통하여 가져온다.")
    @Test
    fun `select all normal user by email`() {
        // given
        val email = "test@test.com"
        val normalUser2 = TestUserGenerator.normalUser(email = email)
        normalUserRepositoryImpl.save(normalUser)
        normalUserRepositoryImpl.save(normalUser2)

        // when
        val normalUserEntities = normalUserRepositoryImpl.find(email = email)

        // then
        assertThat(normalUserEntities.size).isEqualTo(1)
    }

    @DisplayName("등록되어 있는 일반 유저 리스트를 nickname 검색을 통하여 가져온다.")
    @Test
    fun `select all normal user by nickname`() {
        // given
        val nickname = "test"
        val normalUser2 = TestUserGenerator.normalUser(nickname = nickname)
        normalUserRepositoryImpl.save(normalUser)
        normalUserRepositoryImpl.save(normalUser2)

        // when
        val normalUserEntities = normalUserRepositoryImpl.find(nickname = nickname)

        // then
        assertThat(normalUserEntities.size).isEqualTo(1)
    }

    @DisplayName("등록되어 있는 일반 유저 리스트를 UserStatus 검색을 통하여 가져온다.")
    @Test
    fun `select all normal user by userStatus`() {
        // given
        val normalUser2 = TestUserGenerator.normalUser()
        normalUserRepositoryImpl.save(normalUser)
        normalUserRepositoryImpl.save(normalUser2)

        // when
        val normalUserEntities = normalUserRepositoryImpl.find(status = UserStatus.REGISTERED)

        // then
        assertThat(normalUserEntities.size).isEqualTo(2)
    }

}
