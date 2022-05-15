package com.example.user.adpter.out.repository

import com.example.exception.CustomException
import com.example.exception.ErrorCode
import com.example.user.adpter.out.NormalUserRepositoryImpl
import com.example.user.adpter.out.StreamerUserRepositoryImpl
import com.example.user.config.TestBeanConfig
import com.example.user.domain.model.NormalUser
import com.example.user.domain.model.StreamerUser
import com.example.user.domain.model.StreamerUserStatus
import com.example.user.util.TestUserGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import java.util.*


@DataJpaTest
@Import(value = [TestBeanConfig::class])
class StreamerUserRepositoryImplTest {

    private lateinit var normalUser: NormalUser
    private lateinit var streamerUser: StreamerUser

    @Autowired
    private lateinit var normalUserRepositoryImpl: NormalUserRepositoryImpl

    @Autowired
    private lateinit var streamerUserRepositoryImpl: StreamerUserRepositoryImpl

    @BeforeEach
    fun setup() {
        normalUser = TestUserGenerator.normalUser()
        normalUserRepositoryImpl.save(normalUser)
        streamerUser = TestUserGenerator.streamUser(id = normalUser.id)
    }

    @DisplayName("스트리머 유저 정보를 저장한다.")
    @Test
    fun `save streamer user`() {
        // when
        val streamerUserEntity = streamerUserRepositoryImpl.save(streamerUser)

        // then
        assertThat(streamerUserEntity.id).isEqualTo(streamerUser.id)
    }

    @DisplayName("스트리머 유저를 ID를 통하여 검색한다.")
    @Test
    fun `select streamer user`() {
        // given
        streamerUserRepositoryImpl.save(streamerUser)

        // when
        val findStreamerUser = streamerUserRepositoryImpl.findById(streamerUser.id)

        // then
        assertThat(findStreamerUser.id).isEqualTo(streamerUser.id)
    }

    @DisplayName("스트리머 유저를 존재하지 않는 ID를 통하여 검색 하면 Custom Exception을 발생 시킨다.")
    @Test
    fun `select streamer user if not exists throw exception`() {
        // when
        val exception = assertThrows<CustomException> {
            streamerUserRepositoryImpl.findById(UUID.randomUUID())
        }

        // then
        assertThat(exception.errorCode).isEqualTo(ErrorCode.ENTITY_NOT_FOUND)
    }

    @DisplayName("ID에 해당하는 스트리머 유저가 존재하는지 확인한다. - 존재할 경우 true 반환")
    @Test
    fun `exists streamer user by streamer id if exists`() {
        // given
        streamerUserRepositoryImpl.save(streamerUser)

        // when
        val isExists = streamerUserRepositoryImpl.existsById(streamerUser.id)

        // then
        assertThat(isExists).isTrue
    }

    @DisplayName("ID에 해당하는 스트리머 유저가 존재하는지 확인한다. - 존재하지 않을 경우 false 반환")
    @Test
    fun `exists streamer user by streamer id if not exists`() {
        // when
        val isExists = streamerUserRepositoryImpl.existsById(streamerUser.id)

        // then
        assertThat(isExists).isFalse
    }

    @DisplayName("등록되어 있는 스트리머 유저 리스트를 스트리머 유저 상태값을 통하여 가져온다.")
    @Test
    fun `select all streamer user by streamer status`() {
        // given
        streamerUserRepositoryImpl.save(streamerUser)
        val normalUser2 = TestUserGenerator.normalUser()
        normalUserRepositoryImpl.save(normalUser2)
        streamerUserRepositoryImpl.save(TestUserGenerator.streamUser(id = normalUser2.id))

        // when
        val streamerUsers = streamerUserRepositoryImpl.findAllByStatus(StreamerUserStatus.PENDING)

        // then
        assertThat(streamerUsers.size).isEqualTo(2)
    }

    @DisplayName("스트리머 유저 리스트를 저장한다.")
    @Test
    fun `save streamer user list`() {
        // given
        val normalUser2 = TestUserGenerator.normalUser()
        normalUserRepositoryImpl.save(normalUser2)
        val streamerUsers = listOf(streamerUser, TestUserGenerator.streamUser(id = normalUser2.id))

        // when
        val streamerUserEntities = streamerUserRepositoryImpl.saveAll(streamerUsers)

        // then
        assertThat(streamerUserEntities.size).isEqualTo(2)
    }

    @DisplayName("스트리머 유저 리스트를 저장시 normal user id 가 존재하지 않으면 해당 데이터를 제외하고 저장한다.")
    @Test
    fun `save streamer user list if normal user id exists`() {
        // given
        val normalUser2 = TestUserGenerator.normalUser()
        normalUserRepositoryImpl.save(normalUser2)
        val streamerUsers = listOf(streamerUser, TestUserGenerator.streamUser(id = normalUser2.id), TestUserGenerator.streamUser())

        // when
        val streamerUserEntities = streamerUserRepositoryImpl.saveAll(streamerUsers)

        // then
        assertThat(streamerUserEntities.size).isEqualTo(2)
    }

    @DisplayName("등록되어 있는 스트리머 유저 리스트를 스트리머 닉네임과 스트리머 유저 상태값을 통하여 가져온다.")
    @Test
    fun `select all streamer user by streamer nickname, streamer status`() {
        // given
        streamerUserRepositoryImpl.save(streamerUser)
        val normalUser2 = TestUserGenerator.normalUser()
        normalUserRepositoryImpl.save(normalUser2)
        streamerUserRepositoryImpl.save(TestUserGenerator.streamUser(id = normalUser2.id))

        // when
        val streamerUsers = streamerUserRepositoryImpl.findStreamers(streamerUser.streamerNickname, null)

        // then
        assertThat(streamerUsers.size).isEqualTo(1)
    }

}
